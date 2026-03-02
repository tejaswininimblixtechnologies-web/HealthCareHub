package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patient")
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientRepository patientRepository;
    private final PrescriptionService prescriptionService;

    public PatientController(PatientRepository patientRepository,
                             PrescriptionService prescriptionService) {
        this.patientRepository = patientRepository;
        this.prescriptionService = prescriptionService;
    }

    // ✅ Save patient
    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // ✅ Get all patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // ✅ NEW: Get prescriptions by patient ID
    // URL: http://localhost:8080/api/patient/1/prescriptions
    @GetMapping("/{patientId}/prescriptions")
    public List<Prescription> getPrescriptionsByPatient(@PathVariable Long patientId) {
        return (List<Prescription>) prescriptionService.getPrescriptionsByPatientId(patientId);
    }
}
