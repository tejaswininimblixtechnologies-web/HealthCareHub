package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.service.DoctorService;
import nimblix.in.HealthCareHub.service.LabResultService;
import nimblix.in.HealthCareHub.serviceImpl.EmergencyAlertService;
import nimblix.in.HealthCareHub.serviceImpl.LowStockAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * DoctorController
 *
 * Handles all doctor-side operations:
 *  - Lab result management (create + notify patient)
 *  - Emergency alert broadcasting
 *  - Medicine low-stock check
 */
@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final LabResultService labResultService;
    private final EmergencyAlertService emergencyAlertService;
    private final LowStockAlertService lowStockAlertService;

    /**
     * Create a lab result.
     * If status is "READY", automatically emails the patient.
     * POST /api/doctor/lab
     */
    @PostMapping("/lab")
    public ResponseEntity<LabResult> createLabResult(@RequestBody LabResult labResult) {
        LabResult saved = labResultService.createLabResult(labResult);
        return ResponseEntity.ok(saved);
    }

    /**
     * Mark an existing lab result as ready and notify the patient.
     * POST /api/doctor/lab/{id}/ready
     */
    @PostMapping("/lab/{id}/ready")
    public ResponseEntity<String> markLabResultReady(@PathVariable Long id) {
        labResultService.notifyPatient(id);
        return ResponseEntity.ok("Patient notified successfully!");
    }
    /**
     * Send an emergency alert to the admin.
     * POST /api/doctor/emergency/alert
     */
    @PostMapping("/emergency/alert")
    public ResponseEntity<String> sendEmergencyAlert(@RequestParam String message) {
        emergencyAlertService.sendAlert(message);
        return ResponseEntity.ok("Emergency alert sent to admin!");
    }

     /**
     * Manually trigger a low-stock check across all medicines.
     * Sends an email alert for every medicine below threshold.
     * POST /api/doctor/medicine/check-low-stock
     */
    @PostMapping("/medicine/check-low-stock")
    public ResponseEntity<String> checkLowStock() {
        lowStockAlertService.checkLowStock();
        return ResponseEntity.ok("Low stock check completed!");
    }
}
