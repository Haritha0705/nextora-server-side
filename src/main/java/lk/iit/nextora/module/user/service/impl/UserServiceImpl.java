package lk.iit.nextora.module.user.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lk.iit.nextora.common.exception.custom.BadRequestException;
import lk.iit.nextora.common.exception.custom.ResourceNotFoundException;
import lk.iit.nextora.config.redis.CacheService;
import lk.iit.nextora.config.redis.RedisConfig.CacheNames;
import lk.iit.nextora.module.auth.entity.BaseUser;
import lk.iit.nextora.module.auth.mapper.UserResponseMapper;
import lk.iit.nextora.module.auth.service.AuthenticationService;
import lk.iit.nextora.module.user.dto.request.ChangePasswordRequest;
import lk.iit.nextora.module.user.dto.request.UpdateProfileRequest;
import lk.iit.nextora.module.user.dto.response.UserProfileResponse;
import lk.iit.nextora.module.user.dto.response.UserSummaryResponse;
import lk.iit.nextora.module.user.mapper.UserProfileMapper;
import lk.iit.nextora.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of UserService for user profile management.
 *
 * Caching Strategy:
 * - User profiles cached for 15 minutes
 * - Users list cached for 5 minutes
 * - Cache evicted on profile updates
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private final AuthenticationService authenticationService;
    private final UserProfileMapper userProfileMapper;
    private final UserResponseMapper userResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final CacheService cacheService;

    @Override
    @Transactional(readOnly = true)
    public UserProfileResponse getCurrentUserProfile() {
        BaseUser currentUser = getCurrentAuthenticatedUser();
        log.debug("Fetching profile for user: {}", currentUser.getEmail());

        // Try to get from cache first
        return cacheService.getOrCompute(
                "user:profile:" + currentUser.getId(),
                UserProfileResponse.class,
                () -> userProfileMapper.toFullProfileResponse(currentUser, userResponseMapper),
                java.time.Duration.ofMinutes(15)
        );
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheNames.USER_PROFILE_CACHE, key = "#result.id"),
            @CacheEvict(value = CacheNames.USERS_LIST_CACHE, allEntries = true)
    })
    public UserProfileResponse updateCurrentUserProfile(UpdateProfileRequest request) {
        BaseUser currentUser = getCurrentAuthenticatedUser();
        log.info("Updating profile for user: {}", currentUser.getEmail());

        // Update fields if provided
        if (request.getFirstName() != null && !request.getFirstName().isBlank()) {
            currentUser.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null && !request.getLastName().isBlank()) {
            currentUser.setLastName(request.getLastName());
        }
        if (request.getPhone() != null) {
            currentUser.setPhoneNumber(request.getPhone());
        }

        entityManager.merge(currentUser);
        entityManager.flush();

        // Evict user-specific caches
        cacheService.evictUserProfile(currentUser.getId());
        cacheService.evictUsersList();

        log.info("Profile updated successfully for user: {}", currentUser.getEmail());
        return userProfileMapper.toFullProfileResponse(currentUser, userResponseMapper);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        BaseUser currentUser = getCurrentAuthenticatedUser();
        log.info("Password change requested for user: {}", currentUser.getEmail());

        // Validate passwords match
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("New passwords do not match");
        }

        // Validate current password
        if (!passwordEncoder.matches(request.getCurrentPassword(), currentUser.getPassword())) {
            throw new BadRequestException("Current password is incorrect");
        }

        // Check new password is different
        if (passwordEncoder.matches(request.getNewPassword(), currentUser.getPassword())) {
            throw new BadRequestException("New password must be different from current password");
        }

        // Update password
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        entityManager.merge(currentUser);
        entityManager.flush();

        // Evict all user caches (security-sensitive change)
        cacheService.evictAllUserCaches(currentUser.getId());

        log.info("Password changed successfully for user: {}", currentUser.getEmail());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CacheNames.USER_PROFILE_CACHE, key = "#id", unless = "#result == null")
    public UserProfileResponse getUserById(Long id) {
        log.debug("Fetching user by ID: {} (cache miss)", id);

        BaseUser user = entityManager.find(BaseUser.class, id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found", "id", id);
        }

        return userProfileMapper.toFullProfileResponse(user, userResponseMapper);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CacheNames.USERS_LIST_CACHE, key = "'all'", unless = "#result.isEmpty()")
    public List<UserSummaryResponse> getAllUsers() {
        log.debug("Fetching all users (cache miss)");

        List<BaseUser> users = entityManager
                .createQuery("SELECT u FROM BaseUser u ORDER BY u.createdAt DESC", BaseUser.class)
                .getResultList();

        return users.stream()
                .map(userProfileMapper::toSummaryResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CacheNames.USER_PROFILE_CACHE, key = "#id"),
            @CacheEvict(value = CacheNames.USERS_LIST_CACHE, allEntries = true)
    })
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);

        BaseUser user = entityManager.find(BaseUser.class, id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found", "id", id);
        }

        // Soft delete - just disable the account
        user.setEnabled(false);
        entityManager.merge(user);
        entityManager.flush();

        // Evict all caches for this user
        cacheService.evictAllUserCaches(id);

        log.info("User deleted (disabled) successfully: {}", user.getEmail());
    }

    /**
     * Get current authenticated user from security context
     */
    private BaseUser getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BadRequestException("User not authenticated");
        }

        String email = authentication.getName();

        // Try to get from cache by email
        return cacheService.getCachedUserByEmail(email, BaseUser.class)
                .orElseGet(() -> {
                    BaseUser user = authenticationService.findUserByEmail(email)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found", "email", email));
                    // Cache for future requests
                    cacheService.cacheUserByEmail(email, user);
                    return user;
                });
    }
}

