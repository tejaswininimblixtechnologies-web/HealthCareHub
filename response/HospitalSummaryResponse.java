package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalSummaryResponse {

    private Long hospitalId;
    private String name;
    private String city;
    private Double rating;
    private Integer doctorCount;
}