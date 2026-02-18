package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Payment;
import nimblix.in.HealthCareHub.repository.PaymentRepository;
import nimblix.in.HealthCareHub.response.BillingHistoryResponse;
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
    public List<BillingHistoryResponse> getBillingHistoryByPatientId(Long patientId) {
        List<Payment> payments = paymentRepository.findPaymentsByPatientId(patientId);
        return mapPaymentsToBillingHistoryResponse(payments);
    }

    @Override
    public List<BillingHistoryResponse> getBillingHistoryByPatientIdAndStatus(Long patientId, String status) {
        List<Payment> payments = paymentRepository.findPaymentsByPatientIdAndStatus(patientId, status);
        return mapPaymentsToBillingHistoryResponse(payments);
    }

    @Override
    public Double getTotalBillingAmountByPatientId(Long patientId) {
        List<Payment> payments = paymentRepository.findPaymentsByPatientId(patientId);
        return payments.stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    @Override
    public Double getSuccessfulPaymentsTotalByPatientId(Long patientId) {
        List<Payment> payments = paymentRepository.findPaymentsByPatientIdAndStatus(patientId, "SUCCESS");
        return payments.stream()
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    /**
     * Convert Payment entities to BillingHistoryResponse DTOs
     */
    private List<BillingHistoryResponse> mapPaymentsToBillingHistoryResponse(List<Payment> payments) {
        return payments.stream()
                .map(this::convertPaymentToBillingHistory)
                .collect(Collectors.toList());
    }

    /**
     * Convert individual Payment to BillingHistoryResponse
     */
    private BillingHistoryResponse convertPaymentToBillingHistory(Payment payment) {
        return BillingHistoryResponse.builder()
                .paymentId(payment.getId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .paymentDate(payment.getPaymentDate())
                .createdTime(payment.getCreatedTime())
                .updatedTime(payment.getUpdatedTime())
                .appointmentId(payment.getAppointment().getId())
                .appointmentDateTime(payment.getAppointment().getAppointmentDateTime())
                .appointmentStatus(payment.getAppointment().getStatus())
                .doctorId(payment.getAppointment().getDoctor().getId())
                .doctorName(payment.getAppointment().getDoctor().getName())
                .doctorSpecialization(String.valueOf(payment.getAppointment().getDoctor().getSpecialization()))
                .patientId(payment.getAppointment().getPatient().getId())
                .patientName(payment.getAppointment().getPatient().getName())
                .build();
    }
}

