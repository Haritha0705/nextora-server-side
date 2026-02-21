package lk.iit.nextora.module.lostandfound.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MatchSuggestionResponse {

    private Long lostItemId;
    private Long foundItemId;
    private double matchScore;
}
