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
     * Convert individual Payment to BillingHistoryResponse with null-safety checks
     */
    private BillingHistoryResponse convertPaymentToBillingHistory(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment is null");
        }

        var appointment = payment.getAppointment();
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found for payment id: " + payment.getId());
        }

        var patient = appointment.getPatient();
        if (patient == null) {
            throw new IllegalArgumentException("Patient not found for appointment id: " + appointment.getId());
        }

        var doctor = appointment.getDoctor();
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found for appointment id: " + appointment.getId());
        }

        return BillingHistoryResponse.builder()
                .paymentId(payment.getId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .paymentDate(payment.getPaymentDate())
                .createdTime(payment.getCreatedTime())
                .updatedTime(payment.getUpdatedTime())
                .appointmentId(appointment.getId())
                .appointmentDateTime(appointment.getAppointmentDateTime())
                .appointmentStatus(appointment.getStatus())
                .doctorId(doctor.getId())
                .doctorName(doctor.getName())
                .doctorSpecialization(doctor.getSpecialization() != null ? String.valueOf(doctor.getSpecialization()) : null)
                .patientId(patient.getId())
                .patientName(patient.getName())
                .build();
    }
}
