package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.PatientDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDocumentRepository extends JpaRepository<PatientDocument, Long> {

}