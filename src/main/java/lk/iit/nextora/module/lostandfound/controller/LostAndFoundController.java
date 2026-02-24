package lk.iit.nextora.module.lostandfound.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.iit.nextora.common.dto.ApiResponse;
import lk.iit.nextora.common.dto.PagedResponse;
import lk.iit.nextora.module.lostandfound.dto.request.CreateFoundItemRequest;
import lk.iit.nextora.module.lostandfound.dto.request.CreateLostItemRequest;
import lk.iit.nextora.module.lostandfound.dto.response.ItemListResponse;
import lk.iit.nextora.module.lostandfound.dto.response.ItemResponse;
import lk.iit.nextora.module.lostandfound.dto.request.UpdateItemRequest;
import lk.iit.nextora.module.lostandfound.dto.request.SearchItemRequest;
import lk.iit.nextora.module.lostandfound.service.FoundItemService;
import lk.iit.nextora.module.lostandfound.service.LostItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kuppi/items")
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

    // Backward-compatible search (existing)
    @GetMapping("/lost")
    public ApiResponse<ItemListResponse> searchLostItems(
            @Valid @ModelAttribute SearchItemRequest request
    ) {
        return ApiResponse.success(
                lostItemService.searchLostItems(request)
        );
    }

    // Kuppi-style pageable search
    @GetMapping("/lost/search")
    public ApiResponse<PagedResponse<ItemResponse>> searchLostItemsPageable(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        PagedResponse<ItemResponse> response = lostItemService.searchLostItems(keyword, category, pageable);
        return ApiResponse.success(response);
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

    // Backward-compatible search (existing)
    @GetMapping("/found")
    public ApiResponse<ItemListResponse> searchFoundItems(
            @Valid @ModelAttribute SearchItemRequest request
    ) {
        return ApiResponse.success(
                foundItemService.searchFoundItems(request)
        );
    }

    // Kuppi-style pageable search
    @GetMapping("/found/search")
    public ApiResponse<PagedResponse<ItemResponse>> searchFoundItemsPageable(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        PagedResponse<ItemResponse> response = foundItemService.searchFoundItems(keyword, category, pageable);
        return ApiResponse.success(response);
    }
}
