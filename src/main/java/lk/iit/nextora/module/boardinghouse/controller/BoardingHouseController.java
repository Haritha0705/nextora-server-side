package lk.iit.nextora.module.boardinghouse.controller;

import jakarta.validation.Valid;
import lk.iit.nextora.common.constants.ApiConstants;
import lk.iit.nextora.common.dto.ApiResponse;
import lk.iit.nextora.module.boardinghouse.dto.request.CreateBoardingHouseRequest;
import lk.iit.nextora.module.boardinghouse.dto.response.BoardingHouseResponse;
import lk.iit.nextora.module.boardinghouse.service.BoardingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.BOARDINGHOUSE_PUBLIC)
@RequiredArgsConstructor
public class BoardingHouseController {

    private final BoardingHouseService service;

    @PostMapping(ApiConstants.BOARDINGHOUSE_HOUSES)
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BoardingHouseResponse> create(
            @Valid @RequestBody CreateBoardingHouseRequest request) {

        return ApiResponse.success(
                "Created successfully",
                service.create(request)
        );
    }

    @GetMapping(ApiConstants.BOARDINGHOUSE_HOUSES_BY_ID)
    public ApiResponse<BoardingHouseResponse> getById(
            @PathVariable Long houseId) {

        return ApiResponse.success(
                "Retrieved successfully",
                service.getById(houseId)
        );
    }

    @DeleteMapping(ApiConstants.BOARDINGHOUSE_HOUSES_BY_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> delete(
            @PathVariable Long houseId) {

        service.delete(houseId);

        return ApiResponse.success("Deleted successfully");
    }
}