package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrescriptionResponse {

    private Long id;
    private Long doctorId;
    private String medicine;
}