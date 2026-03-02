package nimblix.in.HealthCareHub.request;



import lombok.Data;
import java.time.LocalDate;

@Data
public class AdmissionRequest {

    private Long hospitalId;      // Required
    private String admissionDate;
    private String reason;
    private String roomNumber;
}