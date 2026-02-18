package lk.iit.nextora.module.boardinghouse.service;

import lk.iit.nextora.common.dto.PagedResponse;
import lk.iit.nextora.module.boardinghouse.dto.request.CreateBoardingHouseRequest;
import lk.iit.nextora.module.boardinghouse.dto.request.UpdateBoardingHouseRequest;
import lk.iit.nextora.module.boardinghouse.dto.response.BoardingHouseResponse;
import org.springframework.data.domain.Pageable;

public interface BoardingHouseService {

    BoardingHouseResponse create(CreateBoardingHouseRequest request);

    BoardingHouseResponse update(Long id, UpdateBoardingHouseRequest request);

    void delete(Long id);

    BoardingHouseResponse getById(Long id);

    PagedResponse<BoardingHouseResponse> getAll(Pageable pageable);

    PagedResponse<BoardingHouseResponse> filter(String city,
                                                Double minRent,
                                                Double maxRent,
                                                String gender,
                                                Boolean withFood,
                                                Boolean withFurniture,
                                                Pageable pageable);
}
