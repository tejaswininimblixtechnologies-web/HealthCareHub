package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/revenue")
    public ResponseEntity<?> getRevenueSummary(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        Double revenue = financeService.getRevenueSummary(startDate, endDate);
        return ResponseEntity.ok(revenue);
    }
}