package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Review;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.ReviewRepository;
import nimblix.in.HealthCareHub.request.ReviewRequest;
import nimblix.in.HealthCareHub.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             HospitalRepository hospitalRepository) {
        this.reviewRepository = reviewRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Review addReview(Long hospitalId,
                            ReviewRequest request,
                            Long patientId) {

        hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Review review = Review.builder()
                .rating(request.getRating())
                .comment(request.getComment())
                .patientId(patientId)
                .hospitalId(hospitalId)
                .verified(true)
                .build();

        return reviewRepository.save(review);
    }
}