package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.LabResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabResultRepository extends JpaRepository<LabResult, Long> {

    List<LabResult> findByPatientId(Long patientId);
}