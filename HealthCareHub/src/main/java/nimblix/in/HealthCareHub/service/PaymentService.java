package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Payment;
import java.util.List;

public interface PaymentService {

    List<Payment> getPaymentsByPatientId(Long patientId);

    Payment savePayment(Payment payment);
}