package nimblix.in.HealthCareHub.repository;


import nimblix.in.HealthCareHub.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface AdmissionRepository
        extends JpaRepository<Admission, Long> {

    List<Admission> findByPatientId(Long patientId);
}


