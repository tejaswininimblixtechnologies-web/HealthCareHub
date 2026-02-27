package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // AND-based dynamic search using @Query
    @Query("SELECT p FROM Patient p " +
            "WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:bloodGroup IS NULL OR p.bloodGroup = :bloodGroup) " +
            "AND (:phone IS NULL OR p.phone LIKE CONCAT('%', :phone, '%'))")
    List<Patient> searchPatients(@Param("name") String name,
                                 @Param("bloodGroup") String bloodGroup,
                                 @Param("phone") String phone);
}