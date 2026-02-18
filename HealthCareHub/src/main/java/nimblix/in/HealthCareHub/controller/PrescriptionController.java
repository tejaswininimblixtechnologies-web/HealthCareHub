package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin(origins = "*")
public class PrescriptionController {
    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        Prescription saved = prescriptionService.savePrescription(prescription);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }





    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * GET endpoint to retrieve all prescriptions for a specific patient
     * URL: /api/prescriptions/patient/{patientId}
     * Example: http://localhost:8080/api/prescriptions/patient/1
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByPatient(@PathVariable Long patientId) {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptionsByPatientId(patientId);
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);




    }
}

