package lk.iit.nextora.module.auth.service;

import lk.iit.nextora.module.auth.entity.BaseUser;

/**
 * Service for sending emails including verification emails.
 */
public interface EmailService {

    /**
     * Send email verification link to user.
     *
     * @param user  the user to send verification email to
     * @param token the verification token
     */
    void sendVerificationEmail(BaseUser user, String token);

    /**
     * Send password reset email.
     *
     * @param user     the user
     * @param token    the reset token
     */
    void sendPasswordResetEmail(BaseUser user, String token);

    /**
     * Send account activation confirmation email.
     *
     * @param user the user whose account was activated
     */
    void sendAccountActivatedEmail(BaseUser user);
}
