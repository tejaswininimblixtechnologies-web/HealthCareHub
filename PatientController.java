package com.hospital.hospitalmanagement.Controller;

import com.hospital.hospitalmanagement.entity.Branch;
import com.hospital.hospitalmanagement.entity.Patient;
import com.hospital.hospitalmanagement.repository.BranchRepository;
import com.hospital.hospitalmanagement.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;
    private final BranchRepository branchRepository;

    public PatientController(PatientRepository patientRepository,
                             BranchRepository branchRepository) {
        this.patientRepository = patientRepository;
        this.branchRepository = branchRepository;
    }

    // Add Patient
    @PostMapping("/{branchId}")
    public Patient addPatient(@PathVariable Long branchId,
                              @RequestBody Patient patient) {

        Branch branch = branchRepository.findById(branchId).orElseThrow();
        patient.setBranch(branch);

        return patientRepository.save(patient);
    }

    // Transfer Patient
    @PutMapping("/{patientId}/transfer/{branchId}")
    public Patient transferPatient(@PathVariable Long patientId,
                                   @PathVariable Long branchId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Branch branch = branchRepository.findById(branchId).orElseThrow();

        patient.setBranch(branch);
        return patientRepository.save(patient);
    }

    // Get Patients By Branch
    @GetMapping("/branch/{branchId}")
    public List<Patient> getPatientsByBranch(@PathVariable Long branchId) {
        return patientRepository.findByBranchId(branchId);
    }
}