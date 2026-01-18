package lk.iit.nextora.module.auth.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lk.iit.nextora.module.auth.entity.BaseUser;
import lk.iit.nextora.module.auth.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from:noreply@nextora.lk}")
    private String fromEmail;

    @Value("${app.mail.from-name:Nextora}")
    private String fromName;

    @Value("${app.base-url:http://localhost:8081}")
    private String baseUrl;

    @Override
    @Async
    public void sendVerificationEmail(BaseUser user, String token) {
        String verificationLink = baseUrl + "/api/v1/auth/verify-email?token=" + token;

        String subject = "Verify Your Email - Nextora";
        String htmlContent = buildVerificationEmailHtml(user.getFirstName(), verificationLink);

        sendHtmlEmail(user.getEmail(), subject, htmlContent);
        log.info("Verification email sent to: {}", maskEmail(user.getEmail()));
    }

    @Override
    @Async
    public void sendPasswordResetEmail(BaseUser user, String token) {
        String resetLink = baseUrl + "/api/v1/auth/reset-password?token=" + token;

        String subject = "Reset Your Password - Nextora";
        String htmlContent = buildPasswordResetEmailHtml(user.getFirstName(), resetLink);

        sendHtmlEmail(user.getEmail(), subject, htmlContent);
        log.info("Password reset email sent to: {}", maskEmail(user.getEmail()));
    }

    @Override
    @Async
    public void sendAccountActivatedEmail(BaseUser user) {
        String subject = "Account Activated - Nextora";
        String htmlContent = buildAccountActivatedEmailHtml(user.getFirstName());

        sendHtmlEmail(user.getEmail(), subject, htmlContent);
        log.info("Account activation confirmation email sent to: {}", maskEmail(user.getEmail()));
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail, fromName);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            log.debug("Email sent successfully to: {}", maskEmail(to));
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", maskEmail(to), e.getMessage());
            // Don't throw - allow the application to continue even if email fails
            // In production, you might want to queue this for retry
        } catch (Exception e) {
            log.error("Unexpected error sending email to {}: {}", maskEmail(to), e.getMessage());
            // Don't throw - allow the application to continue even if email fails
        }
    }

    private String buildVerificationEmailHtml(String firstName, String verificationLink) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background-color: #4F46E5; color: white; padding: 20px; text-align: center; }
                    .content { padding: 30px; background-color: #f9f9f9; }
                    .button { display: inline-block; padding: 12px 30px; background-color: #4F46E5; color: white; 
                              text-decoration: none; border-radius: 5px; margin: 20px 0; }
                    .footer { padding: 20px; text-align: center; font-size: 12px; color: #666; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Welcome to Nextora!</h1>
                    </div>
                    <div class="content">
                        <h2>Hello %s,</h2>
                        <p>Thank you for registering with Nextora. Please verify your email address by clicking the button below:</p>
                        <p style="text-align: center;">
                            <a href="%s" class="button">Verify Email</a>
                        </p>
                        <p>Or copy and paste this link in your browser:</p>
                        <p style="word-break: break-all; color: #4F46E5;">%s</p>
                        <p><strong>This link will expire in 24 hours.</strong></p>
                        <p>If you didn't create an account, you can safely ignore this email.</p>
                    </div>
                    <div class="footer">
                        <p>&copy; 2026 Nextora. All rights reserved.</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(firstName, verificationLink, verificationLink);
    }

    private String buildPasswordResetEmailHtml(String firstName, String resetLink) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background-color: #4F46E5; color: white; padding: 20px; text-align: center; }
                    .content { padding: 30px; background-color: #f9f9f9; }
                    .button { display: inline-block; padding: 12px 30px; background-color: #4F46E5; color: white; 
                              text-decoration: none; border-radius: 5px; margin: 20px 0; }
                    .footer { padding: 20px; text-align: center; font-size: 12px; color: #666; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Password Reset</h1>
                    </div>
                    <div class="content">
                        <h2>Hello %s,</h2>
                        <p>We received a request to reset your password. Click the button below to reset it:</p>
                        <p style="text-align: center;">
                            <a href="%s" class="button">Reset Password</a>
                        </p>
                        <p>Or copy and paste this link in your browser:</p>
                        <p style="word-break: break-all; color: #4F46E5;">%s</p>
                        <p><strong>This link will expire in 1 hour.</strong></p>
                        <p>If you didn't request a password reset, you can safely ignore this email.</p>
                    </div>
                    <div class="footer">
                        <p>&copy; 2026 Nextora. All rights reserved.</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(firstName, resetLink, resetLink);
    }

    private String buildAccountActivatedEmailHtml(String firstName) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background-color: #10B981; color: white; padding: 20px; text-align: center; }
                    .content { padding: 30px; background-color: #f9f9f9; }
                    .button { display: inline-block; padding: 12px 30px; background-color: #10B981; color: white; 
                              text-decoration: none; border-radius: 5px; margin: 20px 0; }
                    .footer { padding: 20px; text-align: center; font-size: 12px; color: #666; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>Account Activated!</h1>
                    </div>
                    <div class="content">
                        <h2>Hello %s,</h2>
                        <p>Great news! Your email has been verified and your account is now active.</p>
                        <p>You can now log in to Nextora and start using all features.</p>
                        <p style="text-align: center;">
                            <a href="%s/login" class="button">Log In Now</a>
                        </p>
                        <p>Thank you for joining Nextora!</p>
                    </div>
                    <div class="footer">
                        <p>&copy; 2026 Nextora. All rights reserved.</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(firstName, "https://nextora.lk");
    }

    private String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "***";
        }
        int atIndex = email.indexOf("@");
        if (atIndex <= 2) {
            return "***" + email.substring(atIndex);
        }
        return email.charAt(0) + "***" + email.substring(atIndex - 1);
    }
}
