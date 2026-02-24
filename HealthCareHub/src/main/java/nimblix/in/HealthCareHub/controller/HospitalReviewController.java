package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.service.ReviewService;
import nimblix.in.HealthCareHub.model.Review;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}/reviews")
    public List<Review> getReviewsByHospital(@PathVariable Long id) {
        return reviewService.getReviewsByHospitalId(id);
    }
    @PostMapping("/{id}/reviews")
    public Review addReviewToHospital(@PathVariable Long id,
                                      @RequestBody Review review) {
        return reviewService.addReviewToHospital(id, review);
    }
}