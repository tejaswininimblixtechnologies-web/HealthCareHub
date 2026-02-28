package nimblix.in.HealthCareHub.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LabResultResponse {

    private Long id;
    private String testName;
    private String resultValue;
    private String status;

    private Long patientId;
    private String patientName;

    private Long doctorId;
    private String doctorName;

    private String uploadedAt;
    private String createdTime;
    private String updatedTime;
}