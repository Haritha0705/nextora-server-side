package lk.iit.nextora.module.auth.strategy;

import lk.iit.nextora.common.enums.StudentRoleType;
import lk.iit.nextora.common.exception.custom.BadRequestException;
import lk.iit.nextora.common.util.StringUtils;
import lk.iit.nextora.common.util.ValidationUtils;
import lk.iit.nextora.module.auth.dto.request.RegisterRequest;
import lk.iit.nextora.module.auth.dto.request.StudentRegisterRequest;
import lk.iit.nextora.module.auth.entity.BaseUser;
import lk.iit.nextora.module.auth.entity.Student;
import lk.iit.nextora.module.auth.mapper.UserMapper;
import lk.iit.nextora.module.auth.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentRegistrationStrategy implements RegistrationStrategy {

    private final StudentRepository studentRepository;
    private final UserMapper userMapper;

    @Override
    public void validate(RegisterRequest request) {
        if (!(request instanceof StudentRegisterRequest studentRequest)) {
            throw new BadRequestException("Invalid request type for student registration");
        }

        // Check if student ID already exists
        if (studentRepository.existsByStudentId(studentRequest.getStudentId())) {
            throw new BadRequestException("Student ID already exists");
        }

        // Validate date of birth
        if (studentRequest.getDateOfBirth() != null) {
            if (studentRequest.getDateOfBirth().isAfter(LocalDate.now().minusYears(16))) {
                throw new BadRequestException("Student must be at least 16 years old");
            }
        }

        // Validate role-specific fields based on studentRoleType
        StudentRoleType roleType = studentRequest.getStudentRoleType();
        if (roleType != null && roleType != StudentRoleType.NORMAL) {
            validateRoleSpecificFields(studentRequest, roleType);
        }
    }

    /**
     * Validate required fields based on student sub-role type
     */
    private void validateRoleSpecificFields(StudentRegisterRequest request, StudentRoleType roleType) {
        switch (roleType) {
            case CLUB_MEMBER -> {
                ValidationUtils.requireNonBlank(request.getClubName(), "Club name");
                ValidationUtils.requireNonBlank(request.getClubPosition(), "Club position");
                ValidationUtils.requireNonNull(request.getClubJoinDate(), "Club join date");
            }
            case SENIOR_KUPPI -> {
                ValidationUtils.requireNonEmpty(request.getKuppiSubjects(), "Kuppi subjects");
                ValidationUtils.requireNonBlank(request.getKuppiExperienceLevel(), "Experience level");
            }
            case BATCH_REP -> {
                ValidationUtils.requireNonBlank(request.getBatchRepYear(), "Batch representative year");
                ValidationUtils.requireNonBlank(request.getBatchRepSemester(), "Batch representative semester");
            }
            default -> {
                // NORMAL - no extra validation needed
            }
        }
    }

    @Override
    public BaseUser mapToEntity(RegisterRequest request) {
        StudentRegisterRequest studentRequest = (StudentRegisterRequest) request;

        // Use mapper to convert request to entity
        Student student = userMapper.toStudent(studentRequest);

        // Set common fields from base request
        student.setEmail(studentRequest.getEmail());
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());

        return student;
    }

    @Override
    public void postRegistration(BaseUser user) {
        Student student = (Student) user;
        log.info("Student registered successfully: {} - {} [Sub-Role: {}]",
                student.getStudentId(),
                StringUtils.maskEmail(student.getEmail()),
                student.getStudentRoleDisplayName());
    }
}