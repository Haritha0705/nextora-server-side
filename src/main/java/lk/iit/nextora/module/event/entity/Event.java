package lk.iit.nextora.module.event.entity;

import jakarta.persistence.*;
import lk.iit.nextora.common.entity.BaseEntity;
import lk.iit.nextora.common.enums.EventStatus;
import lk.iit.nextora.module.auth.entity.Student;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Entity representing an Event
 */
@Entity
@Table(name = "events")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class Event extends BaseEntity {
    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    @Builder.Default
    private EventStatus status = EventStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private Student createdBy;

    /* -------- CORE BUSINESS RULES -------- */

    public boolean hasValidTimeRange() {
        return startAt.isBefore(endAt);
    }

    public boolean canEdit() {
        return status == EventStatus.DRAFT;
    }

    public boolean isVisible() {
        return status == EventStatus.PUBLISHED;
    }
}
