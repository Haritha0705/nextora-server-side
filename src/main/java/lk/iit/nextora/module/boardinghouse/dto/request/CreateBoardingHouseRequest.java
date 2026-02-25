package lk.iit.nextora.module.boardinghouse.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lk.iit.nextora.common.enums.Gender;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBoardingHouseRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Double price;

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    @NotNull
    private Gender gender;

    @NotBlank
    private String contactNumber1;

    private String contactNumber2;

    @NotNull
    private Boolean keyMoneyRequired;

    @NotNull
    private Boolean waterBillIncluded;

    @NotNull
    private Boolean electricityBillIncluded;

    @NotNull
    private Boolean foodIncluded;

    @NotNull
    private Boolean furnitureIncluded;

    private Double latitude;

    private Double longitude;
}