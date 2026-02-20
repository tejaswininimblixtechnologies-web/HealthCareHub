package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentReminderScheduler {

    private final AppointmentRepository appointmentRepository;
    private final EmailService emailService;

    // Runs every day at 9 AM
    @Scheduled(cron = "0 0 9 * * ?") // Every day at 9 AM
    public void sendAppointmentReminders() {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDateTime start = tomorrow.atStartOfDay();
        LocalDateTime end = tomorrow.atTime(23, 59, 59);

        List<Appointment> appointments =
                appointmentRepository.findTomorrowAppointments(start, end);

        for (Appointment appointment : appointments) {

            String patientEmail =
                    appointment.getPatient().getUser().getEmail();

            emailService.sendAppointmentReminder(
                    patientEmail,
                    appointment.getDoctor().getName(),
                    appointment.getAppointmentDateTime().toString()
            );
        }
    }

}





