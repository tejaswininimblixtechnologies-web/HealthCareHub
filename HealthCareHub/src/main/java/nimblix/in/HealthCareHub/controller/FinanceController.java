package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.BillingRequest;
import nimblix.in.HealthCareHub.response.BillingResponse;
import nimblix.in.HealthCareHub.service.BillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final BillingService billingService;

    // CREATE BILL
    @PostMapping("/billing")
    public ResponseEntity<BillingResponse> createBill(@RequestBody BillingRequest request) {
        return ResponseEntity.ok(billingService.createBill(request));
    }

    // GET BILL HISTORY
    @GetMapping("/billing/{patientId}")
    public ResponseEntity<List<BillingResponse>> getBills(@PathVariable Long patientId) {
        return ResponseEntity.ok(billingService.getPatientBills(patientId));
    }
}