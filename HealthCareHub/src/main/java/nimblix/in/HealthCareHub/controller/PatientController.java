package nimblix.in.HealthCareHub.controller;


import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient){

        return patientRepository.save(patient);
    }
    @GetMapping
    public List<Patient> getAllPatients(){

        return patientRepository.findAll();
    }
}
