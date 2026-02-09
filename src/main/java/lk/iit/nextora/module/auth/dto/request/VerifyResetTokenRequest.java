package lk.iit.nextora.module.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for verifying the password reset token.
 * User provides the token received via email.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Verify password reset token request")
public class VerifyResetTokenRequest {

    @NotBlank(message = "Token is required")
    @Schema(description = "Password reset token received via email", example = "550e8400-e29b-41d4-a716-446655440000")
    private String token;
}
