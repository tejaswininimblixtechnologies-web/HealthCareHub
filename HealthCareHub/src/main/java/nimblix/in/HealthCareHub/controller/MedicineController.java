package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    // ✅ Expiry Tracking API
    @GetMapping("/expiry")
    public List<Medicine> getExpiringMedicines(@RequestParam int days) {
        return medicineService.getExpiringMedicines(days);
    }

    // ✅ Low Stock Alert API
    @GetMapping("/low-stock")
    public List<Medicine> getLowStockMedicines(@RequestParam int limit) {
        return medicineService.getLowStockMedicines(limit);
    }
}
