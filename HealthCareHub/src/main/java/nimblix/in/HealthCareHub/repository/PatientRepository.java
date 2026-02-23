package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
 nithin-dev
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByphoneNo(String phoneNo);
    boolean existsByPhoneNo(String phoneNo);
}


import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    // Get only active patients
    List<Patient> findByIsDeletedFalse();

}
main
