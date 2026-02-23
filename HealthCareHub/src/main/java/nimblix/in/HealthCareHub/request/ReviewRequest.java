package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    private Integer rating;
    private String comment;
}