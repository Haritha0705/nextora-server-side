package lk.iit.nextora.module.auth.service;

import lk.iit.nextora.module.auth.entity.BaseUser;

/**
 * Service for managing email verification.
 */
public interface EmailVerificationService {

    /**
     * Create verification token and send verification email to user.
     *
     * @param user the user to verify
     */
    void sendVerificationEmail(BaseUser user);

    /**
     * Verify email using token.
     *
     * @param token the verification token
     * @return true if verification was successful
     */
    boolean verifyEmail(String token);

    /**
     * Resend verification email to user.
     *
     * @param email the user's email
     */
    void resendVerificationEmail(String email);

    /**
     * Check if user's email is verified.
     *
     * @param userId the user's ID
     * @return true if email is verified
     */
    boolean isEmailVerified(Long userId);
}
