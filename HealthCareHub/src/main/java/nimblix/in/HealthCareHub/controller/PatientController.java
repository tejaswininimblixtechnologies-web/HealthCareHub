package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import nimblix.in.HealthCareHub.model.Prescription;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    @Autowired
    private PrescriptionService prescriptionService;

    // Add this endpoint method:
    @GetMapping("/{patientId}/prescriptions")
    public List<Prescription> getPrescriptionsByPatient(@PathVariable Long patientId) {
        return prescriptionService.getPrescriptionsByPatientId(patientId);
    }
}
