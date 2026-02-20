package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.serviceImpl.LowStockAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicine")
@RequiredArgsConstructor
public class MedicineController {

    private final LowStockAlertService lowStockAlertService;

    @PostMapping("/check-low-stock")
    public ResponseEntity<String> checkLowStock() {

        lowStockAlertService.checkLowStock();

        return ResponseEntity.ok("Low stock check completed!");
    }
}
