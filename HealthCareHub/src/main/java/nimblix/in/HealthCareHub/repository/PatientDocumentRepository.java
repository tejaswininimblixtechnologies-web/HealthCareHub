package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.PatientDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDocumentRepository extends JpaRepository<PatientDocument, Long> {

    // get all documents belonging to a patient
    List<PatientDocument> findByPatientId(Long patientId);

    List<PatientDocument> findByPatient_Id(Long patientId);
}
