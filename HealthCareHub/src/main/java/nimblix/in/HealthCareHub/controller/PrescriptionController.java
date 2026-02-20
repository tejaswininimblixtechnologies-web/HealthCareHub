package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/{patientId}/prescriptions")
    public ResponseEntity<?> getAllPrescriptionsByPatient(@PathVariable Long patientId) {
        try {
            List<Prescription> prescriptions = prescriptionService.getAllPrescriptionsByPatientId(patientId);
            if (prescriptions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No prescriptions found for patient ID: " + patientId);
            }
            return ResponseEntity.ok(prescriptions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/{patientId}/prescriptions")
    public ResponseEntity<?> createPrescription(
            @PathVariable Long patientId,
            @RequestBody Prescription prescription) {
        try {
            Prescription saved = prescriptionService.savePrescription(patientId, prescription);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/prescriptions/{id}")
    public ResponseEntity<?> getPrescriptionById(@PathVariable Long id) {
        try {
            Optional<Prescription> prescription = prescriptionService.getPrescriptionById(id);
            return prescription.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/prescriptions/{id}")
    public ResponseEntity<?> updatePrescription(
            @PathVariable Long id,
            @RequestBody Prescription prescription) {
        try {
            Prescription updated = prescriptionService.updatePrescription(id, prescription);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/prescriptions/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable Long id) {
        try {
            prescriptionService.deletePrescription(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}