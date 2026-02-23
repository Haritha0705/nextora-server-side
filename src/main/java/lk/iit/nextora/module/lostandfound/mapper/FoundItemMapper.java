package lk.iit.nextora.module.lostandfound.mapper;

import lk.iit.nextora.module.lostandfound.dto.response.ItemResponse;
import lk.iit.nextora.module.lostandfound.entity.FoundItem;

public class FoundItemMapper {

    public static ItemResponse toResponse(FoundItem item) {

        if (item == null) {
            return null;
        }

        return ItemResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .location(item.getLocation())
                .contactNumber(item.getContactNumber())
                .category(item.getCategory() != null ? item.getCategory().getName() : null)
                .active(item.isActive())   // ← THIS fixes your red getActive issue
                .createdAt(item.getCreatedAt())
                .build();
    }
}