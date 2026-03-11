package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalSpecializationResponse {

    private Long hospitalId;
    private String hospitalName;
    private String location;
    private String specialization;

}