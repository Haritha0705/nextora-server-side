package lk.iit.nextora.module.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for token verification in forgot password flow.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Token verification response")
public class VerifyResetTokenResponse {

    @Schema(description = "Whether the token is valid", example = "true")
    private boolean valid;

    @Schema(description = "Verification message", example = "Token is valid. You can now reset your password.")
    private String message;

    @Schema(description = "Masked email of the user", example = "j***e@iit.ac.lk")
    private String maskedEmail;

    @Schema(description = "Remaining token validity in minutes", example = "45")
    private Long remainingMinutes;
}
