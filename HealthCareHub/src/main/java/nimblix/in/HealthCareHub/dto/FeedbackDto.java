package nimblix.in.HealthCareHub.dto;

import lombok.Data;

@Data
public class FeedbackDto {

    private Long patientId;
    private Long doctorId;
    private Integer rating;
    private String comments;
}