package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
    @RequestMapping("/patients")
    public class PatientController{
    @Autowired
        private PatientService patientService;
    @PostMapping("/add")
        public ResponseEntity<?> createPatient(@RequestBody Patient patient){
        Patient savedPatient=patientService.savePatient(patient);
        return ResponseEntity.status(201).body(savedPatient);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getPatientById(@PathVariable Long id){
        Optional<Patient> patient=patientService.getPatientById(id);
        if(patient.isPresent()){
            return ResponseEntity.ok(patient.get());

        }
        return ResponseEntity.status(404).body("Patient not found with id:"+id);
    }
}
