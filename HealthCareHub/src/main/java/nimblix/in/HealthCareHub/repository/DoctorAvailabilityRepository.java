package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    Optional<DoctorAvailability>
    findByDoctorIdAndAvailableDateAndStartTimeAndEndTimeAndIsAvailableTrue(
            Long doctorId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime
    );

}
