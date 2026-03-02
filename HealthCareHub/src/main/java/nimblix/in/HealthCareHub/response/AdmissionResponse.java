package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdmissionResponse {
    private Long id;
    private String admissionDate;
    private String patientName;
    private String hospitalName;
    private String reason;
    private String roomNumber;
}
