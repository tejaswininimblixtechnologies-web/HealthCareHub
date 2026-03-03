package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicalRecordResponse {

    private Long appointmentId;
    private String diagnosis;
    private String treatmentPlan;
    private String clinicalNotes;
    private String appointmentDateTime;
}