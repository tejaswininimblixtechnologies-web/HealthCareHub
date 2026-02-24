package nimblix.in.HealthCareHub.service;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Review;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;   // 👈 Add this

    // ✅ GET
    public List<Review> getReviewsByHospitalId(Long hospitalId) {
        return reviewRepository.findByHospitalId(hospitalId);
    }

    // ✅ POST
    public Review addReviewToHospital(Long hospitalId, Review review) {

        // 1️⃣ Find hospital
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        // 2️⃣ Set hospital to review
        review.setHospital(hospital);

        // 3️⃣ Save review
        return reviewRepository.save(review);
    }
}