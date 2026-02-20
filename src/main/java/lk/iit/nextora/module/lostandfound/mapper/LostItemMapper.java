package lk.iit.nextora.module.lostandfound.mapper;

import lk.iit.nextora.module.lostandfound.dto.response.ItemResponse;
import lk.iit.nextora.module.lostandfound.entity.LostItem;

public class LostItemMapper {

    public static ItemResponse toResponse(LostItem item) {
        return ItemResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .category(item.getCategory().getName())
                .location(item.getLocation())
                .contactNumber(item.getContactNumber())
                .active(item.isActive())
                .build();
    }
}
