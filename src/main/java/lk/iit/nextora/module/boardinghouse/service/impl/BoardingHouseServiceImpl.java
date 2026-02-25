package lk.iit.nextora.module.boardinghouse.service.impl;

import lk.iit.nextora.common.exception.custom.ResourceNotFoundException;
import lk.iit.nextora.module.boardinghouse.dto.request.CreateBoardingHouseRequest;
import lk.iit.nextora.module.boardinghouse.dto.request.UpdateBoardingHouseRequest;
import lk.iit.nextora.module.boardinghouse.dto.response.BoardingHouseResponse;
import lk.iit.nextora.module.boardinghouse.entity.BoardingHouse;
import lk.iit.nextora.module.boardinghouse.mapper.BoardingHouseMapper;
import lk.iit.nextora.module.boardinghouse.repository.BoardingHouseRepository;
import lk.iit.nextora.module.boardinghouse.service.BoardingHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardingHouseServiceImpl implements BoardingHouseService {

    private final BoardingHouseRepository repository;
    private final BoardingHouseMapper mapper;

    @Override
    @Transactional
    public BoardingHouseResponse create(CreateBoardingHouseRequest request) {

        BoardingHouse entity = mapper.toEntity(request);

        BoardingHouse saved = repository.save(entity);

        return mapper.toResponse(saved);
    }

    @Override
    public BoardingHouseResponse getById(Long id) {

        BoardingHouse entity = repository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("BoardingHouse", "id", id));

        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        BoardingHouse entity = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("BoardingHouse", "id", id));

        entity.softDelete();   //

        repository.save(entity);
    }

    @Override
    @Transactional
    public BoardingHouseResponse update(UpdateBoardingHouseRequest request) {

        BoardingHouse entity = repository.findById(request.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("BoardingHouse", "id", request.getId()));

        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setCity(request.getCity());
        entity.setAddress(request.getAddress());
        entity.setPrice(request.getPrice());
        entity.setGender(request.getGender());
        entity.setContactNumber1(request.getContactNumber1());
        entity.setContactNumber2(request.getContactNumber2());
        entity.setKeyMoneyRequired(request.getKeyMoneyRequired());
        entity.setWaterBillIncluded(request.getWaterBillIncluded());
        entity.setElectricityBillIncluded(request.getElectricityBillIncluded());
        entity.setFoodIncluded(request.getFoodIncluded());
        entity.setFurnitureIncluded(request.getFurnitureIncluded());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());

        BoardingHouse saved = repository.save(entity);

        return mapper.toResponse(saved);
    }
}