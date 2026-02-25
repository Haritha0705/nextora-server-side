package lk.iit.nextora.module.boardinghouse.service;

import lk.iit.nextora.module.boardinghouse.dto.request.CreateBoardingHouseRequest;
import lk.iit.nextora.module.boardinghouse.dto.request.UpdateBoardingHouseRequest;
import lk.iit.nextora.module.boardinghouse.dto.response.BoardingHouseResponse;

public interface BoardingHouseService {

    BoardingHouseResponse create(CreateBoardingHouseRequest request);

    BoardingHouseResponse update(UpdateBoardingHouseRequest request);

    BoardingHouseResponse getById(Long id);

    void delete(Long id);
}