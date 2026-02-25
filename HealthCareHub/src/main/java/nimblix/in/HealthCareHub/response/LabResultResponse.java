package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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