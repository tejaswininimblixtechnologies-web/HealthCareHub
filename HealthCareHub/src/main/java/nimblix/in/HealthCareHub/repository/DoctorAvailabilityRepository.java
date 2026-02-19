package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    List<DoctorAvailability> findByDoctorId(Long doctorId);

    // For ADD
    boolean existsByDoctorIdAndAvailableDateAndStartTime(
            Long doctorId,
            LocalDate availableDate,
            LocalTime startTime
    );

    // For UPDATE
    boolean existsByDoctorIdAndAvailableDateAndStartTimeAndIdNot(
            Long doctorId,
            LocalDate availableDate,
            LocalTime startTime,
            Long id
    );
}
