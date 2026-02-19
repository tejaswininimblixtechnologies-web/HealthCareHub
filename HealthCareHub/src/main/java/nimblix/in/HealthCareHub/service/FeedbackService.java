package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.FeedbackRequest;

public interface FeedbackService {

    String submitFeedback(FeedbackRequest request);
}
