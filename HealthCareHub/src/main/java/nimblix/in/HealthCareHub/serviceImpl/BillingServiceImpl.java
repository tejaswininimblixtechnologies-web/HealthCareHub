package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Payment;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PaymentRepository;
import nimblix.in.HealthCareHub.response.BillingHistoryResponse;
import nimblix.in.HealthCareHub.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private PaymentRepository paymentRepository;

    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

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
        // Validate payment
        if (payment == null) {
            throw new IllegalStateException("Payment record is null");
        }
        if (payment.getId() == null) {
            throw new IllegalStateException("Payment record has null id");
        }

        // Validate appointment
        Appointment appointment = payment.getAppointment();
        if (appointment == null || appointment.getId() == null) {
            throw new IllegalStateException("No appointment found for payment id: " + payment.getId());
        }

        // Validate patient
        Patient patient = appointment.getPatient();
        if (patient == null || patient.getId() == null) {
            throw new IllegalStateException("No patient found for appointment id: " + appointment.getId());
        }

        // Validate doctor
        Doctor doctor = appointment.getDoctor();
        if (doctor == null || doctor.getId() == null) {
            throw new IllegalStateException("No doctor found for appointment id: " + appointment.getId());
        }

        // Convert LocalDateTime fields to String safely
        String paymentDateStr = null;
        if (payment.getPaymentDate() != null) {
            paymentDateStr = payment.getPaymentDate().format(ISO_FORMATTER);
        }

        String appointmentDateTimeStr = null;
        if (appointment.getAppointmentDateTime() != null) {
            appointmentDateTimeStr = appointment.getAppointmentDateTime().format(ISO_FORMATTER);
        }

        // Build using all-args constructor to avoid builder method mismatch
        return new BillingHistoryResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentStatus(),
                paymentDateStr,
                payment.getCreatedTime(),
                payment.getUpdatedTime(),
                appointment.getId(),
                appointmentDateTimeStr,
                appointment.getStatus(),
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization() != null ? String.valueOf(doctor.getSpecialization()) : null,
                patient.getId(),
                patient.getName()
        );
    }
}
