package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // GET: http://localhost:8080/api/prescriptions/patient/1
    @GetMapping("/patient/{patientId}")
    public List<Prescription> getPrescriptions(@PathVariable Long patientId) {
        return (List<Prescription>) prescriptionService.getPrescriptionsByPatientId(patientId);
    }
}
