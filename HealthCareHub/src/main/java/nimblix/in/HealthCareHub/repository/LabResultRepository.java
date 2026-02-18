package nimblix.in.HealthCareHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.LabResult;

public interface LabResultRepository extends JpaRepository<LabResult, Long> {
}
