package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.response.BillResponse;
import nimblix.in.HealthCareHub.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping("/patient/{patientId}")
    public List<BillResponse> getBills(@PathVariable Long patientId) {
        return billingService.getBillsByPatient(patientId);
    }
}