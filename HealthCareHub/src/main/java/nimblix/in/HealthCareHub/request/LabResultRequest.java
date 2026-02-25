package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabResultRequest {

    private String testName;
    private String resultValue;
    private String status;
    private Long patientId;
    private Long doctorId;
}