package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.request.RescheduleAppointmentRequest;
import nimblix.in.HealthCareHub.response.AppointmentResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentResponse rescheduleAppointment(
            Long patientId,
            Long appointmentId,
            RescheduleAppointmentRequest request) {

        // 1 Check appointment exists → 404
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Appointment not found"
                        )
                );

        // 2 Validate ownership → 403
        if (!appointment.getPatient().getId().equals(patientId)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Appointment does not belong to this patient"
            );
        }

        // 3 Validate status = BOOKED → 400
        if (!HealthCareConstants.APPOINTMENT_BOOKED
                .equals(appointment.getStatus())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Only booked appointments can be rescheduled"
            );
        }

        // 4 Validate not past → 400
        LocalDateTime newDateTime =
                LocalDateTime.of(request.getNewDate(),
                        request.getNewStartTime());

        if (newDateTime.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot reschedule to past date/time"
            );
        }

        // 5 Update appointment (entity stores String)
        appointment.setAppointmentDateTime(newDateTime.toString());

        Appointment saved = appointmentRepository.save(appointment);

        // 6 Map to DTO
        return AppointmentResponse.builder()
                .appointmentId(saved.getId())
                .patientId(saved.getPatient().getId())
                .doctorId(saved.getDoctor().getId())
                .appointmentDateTime(
                        LocalDateTime.parse(saved.getAppointmentDateTime())
                )
                .status(saved.getStatus())
                .build();
    }
}