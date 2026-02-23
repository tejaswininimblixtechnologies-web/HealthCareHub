package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Review;
import nimblix.in.HealthCareHub.request.ReviewRequest;
import nimblix.in.HealthCareHub.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final ReviewService reviewService;

    // constructor injection (correct way)
    public HospitalController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<?> addReview(
            @PathVariable Long id,
            @RequestBody ReviewRequest request,
            Principal principal) {

        // logged user id from token/session
        Long userId = Long.parseLong(principal.getName());

        Review review = reviewService.addReview(id, request, userId);

        return ResponseEntity.ok(review);
    }
}