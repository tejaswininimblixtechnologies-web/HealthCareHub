package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HospitalStatsResponse {

    private Long hospitalId;
    private Long totalBeds;
    private Long totalDoctors;
    private Long totalPatients;
    private Long patientsServed;
    private Long totalSpecializations;

}