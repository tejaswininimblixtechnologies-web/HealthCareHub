package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class CreateMedicalRecordRequest {

    private Long appointmentId;
    private String diagnosis;
}