package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.request.BillingRequest;
import nimblix.in.HealthCareHub.response.BillingResponse;
import nimblix.in.HealthCareHub.service.FinanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @PostMapping("/billing")
    public ResponseEntity<BillingResponse> createBill(@RequestBody BillingRequest request) {
        return ResponseEntity.ok(financeService.createBill(request));
    }

    @GetMapping("/billing/{patientId}")
    public ResponseEntity<List<BillingResponse>> getBills(@PathVariable Long patientId) {
        return ResponseEntity.ok(financeService.getPatientBills(patientId));
    }
}