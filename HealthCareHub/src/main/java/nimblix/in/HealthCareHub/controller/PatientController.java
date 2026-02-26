package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Payment;
import nimblix.in.HealthCareHub.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PaymentService paymentService;

    @GetMapping("/billing/{patientId}")
    public ResponseEntity<List<Payment>> getBills(@PathVariable Long patientId) {
        return ResponseEntity.ok(paymentService.getPaymentsByPatientId(patientId));
    }

    @PostMapping("/billing")
    public ResponseEntity<Payment> createBill(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.savePayment(payment));
    }
}