package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:phone IS NULL OR p.phone LIKE CONCAT('%', :phone, '%')) AND " +
            "(:bloodGroup IS NULL OR p.bloodGroup = :bloodGroup)")
    List<Patient> searchPatients(
            @Param("name") String name,
            @Param("phone") String phone,
            @Param("bloodGroup") String bloodGroup
    );
}