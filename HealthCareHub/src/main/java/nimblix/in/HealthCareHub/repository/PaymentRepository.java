package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Fetch all payments for a specific patient
     */
    @Query("SELECT p FROM Payment p WHERE p.appointment.patient.id = :patientId ORDER BY p.paymentDate DESC")
    List<Payment> findPaymentsByPatientId(@Param("patientId") Long patientId);

    /**
     * Fetch payments by patient and payment status
     */
    @Query("SELECT p FROM Payment p WHERE p.appointment.patient.id = :patientId AND p.paymentStatus = :status ORDER BY p.paymentDate DESC")
    List<Payment> findPaymentsByPatientIdAndStatus(@Param("patientId") Long patientId, @Param("status") String status);

    /**
     * Fetch all payments for a specific patient with pagination support
     */
    @Query("SELECT p FROM Payment p WHERE p.appointment.patient.id = :patientId ORDER BY p.paymentDate DESC")
    List<Payment> findPaymentsByPatientIdPaginated(@Param("patientId") Long patientId);
}

