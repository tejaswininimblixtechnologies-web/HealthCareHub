package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.repository.ReviewRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByHospitalId(Long hospitalId);

}