package nimblix.in.HealthCareHub.serviceImpl;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.repository.DoctorAvailabilityRepository;
import nimblix.in.HealthCareHub.model.DoctorAvailability;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;


    @Override
    public List<Appointment> filterAppointments(Long doctorId,
                                                LocalDate date,
                                                String status) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        return appointmentRepository
                .findByDoctorIdAndAppointmentDateTimeBetweenAndStatus(
                        doctorId,
                        startOfDay,
                        endOfDay,
                        status
                );
    }

    @Override
    public List<LocalTime> getAvailableSlots(Long doctorId, LocalDate date) {

        // 1 Get doctor availability for that date
        DoctorAvailability availability =
                doctorAvailabilityRepository
                        .findByDoctorIdAndAvailableDateAndIsAvailableTrue(
                                doctorId, date)
                        .orElseThrow(() ->
                                new RuntimeException("Doctor not available on this date"));

        LocalTime startTime = availability.getStartTime();
        LocalTime endTime = availability.getEndTime();

        // 2 Fetch already booked appointments
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<Appointment> bookedAppointments =
                appointmentRepository
                        .findByDoctorIdAndAppointmentDateTimeBetweenAndStatus(
                                doctorId,
                                startOfDay,
                                endOfDay,
                                HealthCareConstants.APPOINTMENT_SCHEDULED
                        );

        // 3 Convert booked times to LocalTime set
        Set<LocalTime> bookedTimes = bookedAppointments.stream()
                .map(a -> a.getAppointmentDateTime().toLocalTime())
                .collect(Collectors.toSet());

        // 4 Generate 20-minute slots
        List<LocalTime> availableSlots = new ArrayList<>();

        LocalTime current = startTime;

        while (current.isBefore(endTime)) {

            if (!bookedTimes.contains(current)) {
                availableSlots.add(current);
            }

            current = current.plusMinutes(20);
        }

        return availableSlots;
    }
}