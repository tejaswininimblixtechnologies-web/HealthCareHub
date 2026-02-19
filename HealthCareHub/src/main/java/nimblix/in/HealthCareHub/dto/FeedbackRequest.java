package nimblix.in.HealthCareHub.dto;

import lombok.Data;

@Data
public class FeedbackRequest {

    private Long patientId;
    private Long doctorId;
    private String comments;
    private Integer rating;
}
