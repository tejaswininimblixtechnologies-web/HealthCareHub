package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.exception.AppointmentNotFoundException;
import nimblix.in.HealthCareHub.exception.SlotNotAvailableException;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.repository.DoctorAvailabilityRepository;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Override
    public Appointment rescheduleAppointment(Long appointmentId,
                                             LocalDateTime newDateTime) {

        // 1 Check appointment exists
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with id: " + appointmentId
                        )
                );

        // 2 Allow only SCHEDULED
        if (!HealthCareConstants.APPOINTMENT_SCHEDULED
                .equalsIgnoreCase(appointment.getStatus())) {

            throw new IllegalStateException(
                    "Only SCHEDULED appointments can be rescheduled"
            );
        }

        // 3 Prevent past date
        if (newDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(
                    "Cannot reschedule to past date/time"
            );
        }

        Long doctorId = appointment.getDoctor().getId();
        LocalDate newDate = newDateTime.toLocalDate();
        LocalTime newStart = newDateTime.toLocalTime();
        LocalTime newEnd = newStart.plusMinutes(20); // slot duration

        // 4 Check doctor availability
        doctorAvailabilityRepository
                .findByDoctorIdAndAvailableDateAndStartTimeAndEndTimeAndIsAvailableTrue(
                        doctorId,
                        newDate,
                        newStart,
                        newEnd
                )
                .orElseThrow(() ->
                        new SlotNotAvailableException(
                                "Doctor not available for selected slot"
                        )
                );

        // 5 Check time overlap
        List<Appointment> existingAppointments =
                appointmentRepository.findAll()
                        .stream()
                        .filter(a -> a.getDoctor().getId().equals(doctorId))
                        .filter(a -> a.getAppointmentDateTime().toLocalDate().equals(newDate))
                        .toList();

        for (Appointment existing : existingAppointments) {

            if (existing.getId().equals(appointmentId)) {
                continue;
            }

            LocalDateTime existingStart = existing.getAppointmentDateTime();
            LocalDateTime existingEnd = existingStart.plusMinutes(20);

            if (newDateTime.isBefore(existingEnd) &&
                    newDateTime.plusMinutes(20).isAfter(existingStart)) {

                throw new SlotNotAvailableException("Slot overlap detected");
            }
        }

        // 6 Update appointment
        appointment.setAppointmentDateTime(newDateTime);

        return appointmentRepository.save(appointment);
    }
}