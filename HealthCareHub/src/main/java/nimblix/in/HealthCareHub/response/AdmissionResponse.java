package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AdmissionResponse {
    private Long id;
    private String patientName;
    private String hospitalName;
    private String reason;
    private String roomNumber;
    private String admissionDate;

}
