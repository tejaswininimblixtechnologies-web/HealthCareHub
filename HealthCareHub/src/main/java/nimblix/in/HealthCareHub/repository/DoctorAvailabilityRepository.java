package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    List<DoctorAvailability> findByDoctorId(Long doctorId);

    // For ADD
    boolean existsByDoctorIdAndAvailableDateAndStartTime(
            Long doctorId,
            String availableDate,
            String startTime
    );

    // For UPDATE
    boolean existsByDoctorIdAndAvailableDateAndStartTimeAndIdNot(
            Long doctorId,
            String availableDate,
            String startTime,
            Long id
    );
}