package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.response.MedicineResponse;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    // Expiry Tracking API
    @GetMapping("/expiry")
    public ResponseEntity<List<MedicineResponse>> getExpiringMedicines(
            @RequestParam int days) {

        return ResponseEntity.ok(
                medicineService.getExpiringMedicines(days));
    }

    // Low Stock Alert
    @GetMapping("/low-stock")
    public ResponseEntity<List<MedicineResponse>> getLowStockMedicines(
            @RequestParam int limit) {

        return ResponseEntity.ok(
                medicineService.getLowStockMedicines(limit)
        );
    }
}