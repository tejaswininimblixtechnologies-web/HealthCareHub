package nimblix.in.HealthCareHub.response;
import nimblix.in.HealthCareHub.response.FeedbackDto;
import lombok.Data;

@Data
public class FeedbackDto {

    private Long patientId;
    private String feedback;
    private Double rating;

}