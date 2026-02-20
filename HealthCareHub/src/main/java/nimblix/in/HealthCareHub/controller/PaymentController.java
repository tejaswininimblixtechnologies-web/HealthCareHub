package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{id}/notify")
    public ResponseEntity<String> notifyPending(@PathVariable Long id) {

        paymentService.notifyPendingPayment(id);

        return ResponseEntity.ok("Payment reminder sent successfully!");
    }
}
