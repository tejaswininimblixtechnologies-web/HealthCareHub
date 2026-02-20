package nimblix.in.HealthCareHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.MedicalRecord;

    public interface MedicalRecordRepository
            extends JpaRepository<nimblix.in.HealthCareHub.model.MedicalRecord, Long> {

    }

