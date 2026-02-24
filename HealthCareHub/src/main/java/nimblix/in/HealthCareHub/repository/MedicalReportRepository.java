package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalReportRepository
        extends JpaRepository<MedicalReport, Long> {

    Optional<MedicalReport> findByPatientId(Long patientId);
}
