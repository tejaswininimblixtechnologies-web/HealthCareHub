package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.dto.RevenueSummaryDTO;
import nimblix.in.HealthCareHub.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping("/summary")
    public RevenueSummaryDTO getSummary() {
        return revenueService.getRevenueSummary();
    }
}
