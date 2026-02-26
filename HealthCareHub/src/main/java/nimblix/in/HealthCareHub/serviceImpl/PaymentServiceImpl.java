package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Payment;
import nimblix.in.HealthCareHub.repository.PaymentRepository;
import nimblix.in.HealthCareHub.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getPaymentsByPatientId(Long patientId) {
        return paymentRepository.findByPatientId(patientId);
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}