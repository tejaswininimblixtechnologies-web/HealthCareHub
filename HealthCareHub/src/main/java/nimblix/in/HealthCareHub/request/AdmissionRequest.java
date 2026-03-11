package nimblix.in.HealthCareHub.request;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AdmissionRequest {
    private Long patientId;
    private Long hospitalId;      // Required
    private String admissionDate;
    private String reason;
    private String roomNumber;
}