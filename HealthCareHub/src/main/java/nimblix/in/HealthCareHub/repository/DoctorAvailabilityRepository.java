package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    List<DoctorAvailability> findByDoctor_IdAndIsAvailableTrue(Long doctorId);

    void deleteByDoctor_Id(Long doctorId);
}