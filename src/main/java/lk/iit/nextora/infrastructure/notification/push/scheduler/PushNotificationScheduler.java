package lk.iit.nextora.infrastructure.notification.push.scheduler;

import lk.iit.nextora.infrastructure.notification.push.service.FcmTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled Jobs for Push Notification Maintenance
 *
 * Design Decisions:
 * 1. Weekly cleanup of inactive tokens
 * 2. Runs at 3 AM on Sundays to minimize impact
 * 3. Configurable retention period
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PushNotificationScheduler {

    private final FcmTokenService fcmTokenService;

    /**
     * Cleanup inactive tokens older than 30 days.
     * Runs every Sunday at 3:00 AM.
     */
    @Scheduled(cron = "0 0 3 * * SUN")
    public void cleanupInactiveTokens() {
        log.info("Starting scheduled cleanup of inactive FCM tokens");

        try {
            int deletedCount = fcmTokenService.cleanupInactiveTokens(30);
            log.info("Scheduled cleanup completed. Deleted {} inactive tokens", deletedCount);
        } catch (Exception e) {
            log.error("Error during scheduled token cleanup", e);
        }
    }
}
