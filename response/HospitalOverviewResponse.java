package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalOverviewResponse {

    private Long hospitalId;
    private String hospitalName;
    private String location;
    private Double rating;
    private Long doctorCount;
    private Integer bedCount;
    private String status;

}