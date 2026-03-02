package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.BillingRequest;
import nimblix.in.HealthCareHub.response.BillingResponse;
import nimblix.in.HealthCareHub.service.BillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.exception.PaymentException;
import java.util.List;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final BillingService billingService;

    // CREATE BILL
    @PostMapping("/billing")
    public ResponseEntity<BillingResponse> createBill(@RequestBody BillingRequest request) {

        if (request == null) {
            throw new PaymentException("Request body cannot be null");
        }

        if (request.getPatientId() == null) {
            throw new PaymentException("Patient ID is required");
        }

        return ResponseEntity.ok(billingService.createBill(request));
    }

    // GET BILL
    @GetMapping("/billing/{patientId}")
    public ResponseEntity<List<BillingResponse>> getBills(@PathVariable Long patientId) {

        if (patientId == null) {
            throw new PaymentException("Patient ID cannot be null");
        }

        return ResponseEntity.ok(billingService.getPatientBills(patientId));
    }
}