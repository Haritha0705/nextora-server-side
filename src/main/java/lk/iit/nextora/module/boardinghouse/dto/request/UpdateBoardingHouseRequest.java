package lk.iit.nextora.module.boardinghouse.dto.request;

import jakarta.validation.constraints.NotNull;
import lk.iit.nextora.common.enums.Gender;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBoardingHouseRequest {

    @NotNull
    private Long id;

    private String title;

    private String description;

    private String city;

    private String address;

    private Double price;

    private Gender gender;

    private String contactNumber1;

    private String contactNumber2;

    private Boolean keyMoneyRequired;

    private Boolean waterBillIncluded;

    private Boolean electricityBillIncluded;

    private Boolean foodIncluded;

    private Boolean furnitureIncluded;

    private Double latitude;

    private Double longitude;
}