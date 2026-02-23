package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PaymentService paymentService;

    /**
      Notify a patient about a pending payment.
     Looks up the payment by ID and sends a reminder email if status is PENDING.
      POST /api/patient/payment/{id}/notify
     */
    @PostMapping("/payment/{id}/notify")
    public ResponseEntity<String> notifyPendingPayment(@PathVariable Long id) {
        paymentService.notifyPendingPayment(id);
        return ResponseEntity.ok("Payment reminder sent successfully!");
    }
}
