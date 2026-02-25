package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class PrescriptionRequest {

    private String medicine;
    private Long doctorId;
}