package lk.iit.nextora.module.lostandfound.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemResponse {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String location;
    private String contactNumber;
    private boolean active;
}
