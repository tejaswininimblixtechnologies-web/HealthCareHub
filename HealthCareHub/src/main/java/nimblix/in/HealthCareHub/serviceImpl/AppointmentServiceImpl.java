package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.exception.AppointmentNotFoundException;
import nimblix.in.HealthCareHub.exception.SlotNotAvailableException;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment rescheduleAppointment(Long appointmentId,
                                             LocalDateTime newDateTime) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with id: " + appointmentId
                        )
                );
        if (!HealthCareConstants.APPOINTMENT_SCHEDULED
                .equalsIgnoreCase(appointment.getStatus())) {
            throw new IllegalStateException(
                    "Only SCHEDULED appointments can be rescheduled"
            );
        }
        Long doctorId = appointment.getDoctor().getId();

        List<Appointment> existingAppointments =
                appointmentRepository.findByDoctorIdAndAppointmentDateTime(
                        doctorId,
                        newDateTime
                );
        boolean conflict = existingAppointments.stream()
                .anyMatch(a -> !a.getId().equals(appointmentId));

        if (conflict) {
            throw new SlotNotAvailableException(
                    "Slot not available for selected time"
            );
        }
        appointment.setAppointmentDateTime(newDateTime);
        return appointmentRepository.save(appointment);
    }
}