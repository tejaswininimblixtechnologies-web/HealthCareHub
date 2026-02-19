package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    // Task 1
    @PostMapping("/provider")
    public ResponseEntity<InsuranceProvider> addProvider(@RequestBody InsuranceProvider provider){
        return ResponseEntity.ok(insuranceService.addProvider(provider));
    }

    // Task 2
    @PostMapping("/assign")
    public ResponseEntity<PatientInsurance> assignInsurance(@RequestBody PatientInsurance patientInsurance){
        return ResponseEntity.ok(insuranceService.assignInsurance(patientInsurance));
    }

    // Task 3
    @GetMapping("/verify/{patientId}")
    public ResponseEntity<String> verifyEligibility(@PathVariable Long patientId){
        boolean eligible = insuranceService.verifyEligibility(patientId);
        return ResponseEntity.ok(eligible ? "Eligible" : "Not Eligible");
    }

    // Task 4
    @PostMapping("/claim")
    public ResponseEntity<InsuranceClaim> submitClaim(@RequestBody InsuranceClaim claim){
        return ResponseEntity.ok(insuranceService.submitClaim(claim));
    }

    // Task 5
    @GetMapping("/claims/{patientId}")
    public ResponseEntity<List<InsuranceClaim>> getClaims(@PathVariable Long patientId){
        return ResponseEntity.ok(insuranceService.getClaimStatus(patientId));
    }
}