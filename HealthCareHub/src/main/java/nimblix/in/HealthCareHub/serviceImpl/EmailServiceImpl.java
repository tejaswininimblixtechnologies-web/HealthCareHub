package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendAppointmentReminder(String to, String doctorName, String dateTime) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Appointment Reminder - HealthCareHub");
        message.setText(
                "Dear Patient,\n\n" +
                        "This is a reminder for your appointment with Dr. " +
                        doctorName + " on " + dateTime +
                        ".\n\nRegards,\nHealthCareHub Team"
        );
        mailSender.send(message);
    }

    @Override
    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void sendEmergencyAlert(String to, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject("EMERGENCY ALERT - HealthCareHub");
        mail.setText(
                "Emergency Alert:\n\n" +
                        message +
                        "\n\nRegards,\nHealthCareHub System"
        );
        mailSender.send(mail);
    }
}



