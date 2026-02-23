package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Payment;
import nimblix.in.HealthCareHub.repository.PaymentRepository;
import nimblix.in.HealthCareHub.service.EmailService;
import nimblix.in.HealthCareHub.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final EmailService emailService;

    @Override
    public void notifyPendingPayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if ("PENDING".equalsIgnoreCase(payment.getPaymentStatus())) {

            String patientEmail =
                    payment.getAppointment()
                            .getPatient()
                            .getUser()
                            .getEmail();

            emailService.sendSimpleMail(
                    patientEmail,
                    "Payment Due Reminder - HealthCareHub",
                    "Dear Patient,\n\n" +
                            "Your payment of Rs. " +
                            payment.getAmount() +
                            " is pending.\n\n" +
                            "Please complete your payment at the earliest.\n\n" +
                            "Regards,\nHealthCareHub"
            );
        }
    }
}
