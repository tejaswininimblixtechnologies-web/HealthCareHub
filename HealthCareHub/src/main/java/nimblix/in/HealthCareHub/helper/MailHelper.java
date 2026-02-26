package nimblix.in.HealthCareHub.helper;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class MailHelper {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:dummy@mail.com}")
    private String fromEmail;

    @Value("${mail.template.reset-password-otp:Hello {name}, your OTP is {otp}}")
    private String otpHtmlTemplate;

    private static final Pattern pattern =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public boolean isValidEmail(String email) {
        return email != null && pattern.matcher(email).matches();
    }

    @Async
    public void sendOtpMail(String toEmail, String name, String otp, String subject) {
        try {
            if (!isValidEmail(toEmail)) return;

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject(subject);

            String body = otpHtmlTemplate
                    .replace("{name}", name == null ? "User" : name)
                    .replace("{otp}", otp == null ? "XXXXXX" : otp);

            helper.setText(body, true);
            mailSender.send(message);

        } catch (Exception e) {
            System.out.println("Mail disabled: " + e.getMessage());
        }
    }

    public static int getSixDigitRandomNumber() {
        return 100000 + new java.util.Random().nextInt(900000);
    }
}