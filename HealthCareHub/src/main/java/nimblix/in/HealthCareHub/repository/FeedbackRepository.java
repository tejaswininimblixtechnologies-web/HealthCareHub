package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

