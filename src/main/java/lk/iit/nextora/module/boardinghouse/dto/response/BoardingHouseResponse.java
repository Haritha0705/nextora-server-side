package lk.iit.nextora.module.boardinghouse.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardingHouseResponse {

    private Long id;

    private String title;

    private String description;

    private Double price;

    private String city;

    private String address;

    private String gender;

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