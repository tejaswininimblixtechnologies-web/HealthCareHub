package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
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
    public List<Appointment> getUpcomingAppointments(Long patientId) {

        return appointmentRepository
                .findByPatientIdAndAppointmentTimeAfter(
                        patientId,
                        LocalDateTime.now()
                );
    }
}

