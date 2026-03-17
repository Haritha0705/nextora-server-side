package lk.iit.nextora.module.intranet.entity;

import jakarta.persistence.*;
import lk.iit.nextora.common.entity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a Students Relations Unit (SRU) category.
 * Can be a root category or a subcategory with associated videos.
 * Contains a bidirectional one-to-many relationship with {@link SruVideo}.
 */
@Entity
@Table(name = "intranet_sru_categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = "category_slug")
})
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SruCategory extends BaseEntity {

    @Column(name = "category_slug", nullable = false, unique = true, length = 200)
    private String categorySlug;

    @Column(name = "category_name", nullable = false, length = 200)
    private String categoryName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "unit_name", length = 300)
    private String unitName;

    @Column(name = "location", length = 500)
    private String location;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "office_hours", columnDefinition = "TEXT")
    private String officeHours;

    @OneToMany(mappedBy = "sruCategory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    @Builder.Default
    private List<SruVideo> videos = new ArrayList<>();

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "is_deleted")
    @Builder.Default
    private Boolean isDeleted = false;

    /**
     * Add a video to this category
     */
    public void addVideo(SruVideo video) {
        videos.add(video);
        video.setSruCategory(this);
    }
}

