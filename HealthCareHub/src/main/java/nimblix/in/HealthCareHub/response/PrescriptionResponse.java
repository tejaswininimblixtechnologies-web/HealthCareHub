package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PrescriptionResponse {

    private Long id;
    private String medicine;
    private Long doctorId;
}