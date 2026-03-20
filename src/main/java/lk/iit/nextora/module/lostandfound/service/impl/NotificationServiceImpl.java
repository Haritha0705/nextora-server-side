package lk.iit.nextora.module.lostandfound.service.impl;

import lk.iit.nextora.module.lostandfound.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyUser(Long studentId, String message) {
        log.info("NOTIFICATION -> student {}: {}", studentId, message);
    }

    @Override
    public void notifyMatchFound(Long studentId, Long lostItemId, Long foundItemId) {
        log.info("MATCH NOTIFICATION -> student {}: lostItem {} matched foundItem {}",
                studentId, lostItemId, foundItemId);
    }

    @Override
    public void notifyClaimApproved(Long studentId, Long claimId) {
        log.info("CLAIM APPROVED -> student {}: claim {} approved", studentId, claimId);
    }

    @Override
    public void notifyClaimRejected(Long studentId, Long claimId, String reason) {
        log.info("CLAIM REJECTED -> student {}: claim {} rejected. Reason: {}",
                studentId, claimId, reason);
    }
}
