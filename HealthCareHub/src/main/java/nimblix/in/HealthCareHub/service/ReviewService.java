package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Review;
import nimblix.in.HealthCareHub.request.ReviewRequest;

public interface ReviewService {

    Review addReview(Long hospitalId, ReviewRequest request, Long patientId);

}