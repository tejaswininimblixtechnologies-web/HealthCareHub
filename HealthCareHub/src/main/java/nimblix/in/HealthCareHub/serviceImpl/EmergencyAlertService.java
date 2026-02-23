package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.service.EmailService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmergencyAlertService {

    private final EmailService emailService;

    private final String ADMIN_EMAIL = "chethankumargs770@gmail.com.com"; // use admin email here

    public void sendAlert(String message) {

        emailService.sendEmergencyAlert(
                ADMIN_EMAIL,
                message
        );
    }
}
