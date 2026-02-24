package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.response.BillResponse;
import nimblix.in.HealthCareHub.service.BillingService;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final BillingService billingService;

    public PatientController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/{patientId}/bills")
    public List<BillResponse> getBillsByPatient(@PathVariable Long patientId) {
        return billingService.getBillsByPatient(patientId);
    }
}