package lk.iit.nextora.module.lostandfound.service;

import lk.iit.nextora.common.dto.PagedResponse;
import lk.iit.nextora.module.lostandfound.dto.request.CreateFoundItemRequest;
import lk.iit.nextora.module.lostandfound.dto.request.SearchItemRequest;
import lk.iit.nextora.module.lostandfound.dto.request.UpdateItemRequest;
import lk.iit.nextora.module.lostandfound.dto.response.ItemListResponse;
import lk.iit.nextora.module.lostandfound.dto.response.ItemResponse;
import org.springframework.data.domain.Pageable;

public interface FoundItemService {

    ItemResponse createFoundItem(CreateFoundItemRequest request);

    ItemResponse updateFoundItem(Long id, UpdateItemRequest request);

    ItemListResponse searchFoundItems(SearchItemRequest request);

    // Kuppi-style pageable search
    PagedResponse<ItemResponse> searchFoundItems(String keyword, String category, Pageable pageable);
}
