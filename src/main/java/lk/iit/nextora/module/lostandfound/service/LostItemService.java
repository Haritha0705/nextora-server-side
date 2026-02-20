package lk.iit.nextora.module.lostandfound.service;

import lk.iit.nextora.module.lostandfound.dto.request.CreateLostItemRequest;
import lk.iit.nextora.module.lostandfound.dto.request.SearchItemRequest;
import lk.iit.nextora.module.lostandfound.dto.request.UpdateItemRequest;
import lk.iit.nextora.module.lostandfound.dto.response.ItemListResponse;
import lk.iit.nextora.module.lostandfound.dto.response.ItemResponse;

public interface LostItemService {

    ItemResponse createLostItem(CreateLostItemRequest request);

    ItemResponse updateLostItem(Long id, UpdateItemRequest request);

    ItemListResponse searchLostItems(SearchItemRequest request);
}
