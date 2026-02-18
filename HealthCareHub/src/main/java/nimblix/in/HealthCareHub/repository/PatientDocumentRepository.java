package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.PatientDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientDocumentRepository extends JpaRepository<PatientDocument, Long> {

    List<PatientDocument> findByPatientId(Long patientId);


}
