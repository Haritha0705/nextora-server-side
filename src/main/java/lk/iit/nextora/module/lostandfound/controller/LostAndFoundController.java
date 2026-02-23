package lk.iit.nextora.module.lostandfound.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.iit.nextora.common.dto.ApiResponse;
import lk.iit.nextora.module.lostandfound.dto.request.CreateFoundItemRequest;
import lk.iit.nextora.module.lostandfound.dto.request.CreateLostItemRequest;
import lk.iit.nextora.module.lostandfound.dto.response.ItemListResponse;
import lk.iit.nextora.module.lostandfound.dto.response.ItemResponse;
import lk.iit.nextora.module.lostandfound.dto.request.UpdateItemRequest;
import lk.iit.nextora.module.lostandfound.dto.request.SearchItemRequest;
import lk.iit.nextora.module.lostandfound.service.FoundItemService;
import lk.iit.nextora.module.lostandfound.service.LostItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lost-found")
@RequiredArgsConstructor
@Tag(name = "Lost & Found")
public class LostAndFoundController {

    private final LostItemService lostItemService;
    private final FoundItemService foundItemService;

    // -------- LOST ITEMS --------

    @PostMapping("/lost")
    public ApiResponse<ItemResponse> createLostItem(
            @Valid @RequestBody CreateLostItemRequest request
    ) {
        return ApiResponse.success(
                lostItemService.createLostItem(request)
        );
    }

    @PutMapping("/lost/{id}")
    public ApiResponse<ItemResponse> updateLostItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateItemRequest request
    ) {
        return ApiResponse.success(
                lostItemService.updateLostItem(id, request)
        );
    }

    @GetMapping("/lost")
    public ApiResponse<ItemListResponse> searchLostItems(
            @Valid SearchItemRequest request
    ) {
        return ApiResponse.success(
                lostItemService.searchLostItems(request)
        );
    }

    // -------- FOUND ITEMS --------

    @PostMapping("/found")
    public ApiResponse<ItemResponse> createFoundItem(
            @Valid @RequestBody CreateFoundItemRequest request
    ) {
        return ApiResponse.success(
                foundItemService.createFoundItem(request)
        );
    }

    @PutMapping("/found/{id}")
    public ApiResponse<ItemResponse> updateFoundItem(
            @PathVariable Long id,
            @Valid @RequestBody UpdateItemRequest request
    ) {
        return ApiResponse.success(
                foundItemService.updateFoundItem(id, request)
        );
    }

    @GetMapping("/found")
    public ApiResponse<ItemListResponse> searchFoundItems(
            @Valid SearchItemRequest request
    ) {
        return ApiResponse.success(
                foundItemService.searchFoundItems(request)
        );
    }
}
