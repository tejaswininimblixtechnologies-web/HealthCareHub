package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.exception.PaymentException;
import nimblix.in.HealthCareHub.model.Payment;
import nimblix.in.HealthCareHub.repository.PaymentRepository;
import nimblix.in.HealthCareHub.request.BillingRequest;
import nimblix.in.HealthCareHub.response.BillingResponse;
import nimblix.in.HealthCareHub.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public BillingResponse createBill(BillingRequest request) {

        if (request == null) {
            throw new PaymentException("Request cannot be null");
        }

        if (request.getPatientId() == null) {
            throw new PaymentException("Patient ID required");
        }

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new PaymentException("Invalid amount");
        }

        Payment payment = new Payment();
        payment.setPatientId(request.getPatientId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setStatus(request.getStatus());

        Payment saved = paymentRepository.save(payment);

        return map(saved);
    }

    @Override
    public List<BillingResponse> getPatientBills(Long patientId) {

        if (patientId == null) {
            throw new PaymentException("Patient ID cannot be null");
        }

        List<Payment> bills = paymentRepository.findByPatientId(patientId);

        if (bills.isEmpty()) {
            throw new PaymentException("No bills found");
        }

        return bills.stream().map(this::map).collect(Collectors.toList());
    }

    private BillingResponse map(Payment p) {
        BillingResponse res = new BillingResponse();
        res.setId(p.getId());
        res.setPatientId(p.getPatientId());
        res.setAmount(p.getAmount());
        res.setPaymentMode(p.getPaymentMode());
        res.setStatus(p.getStatus());
        res.setCreatedAt(p.getCreatedAt());
        return res;
    }
}