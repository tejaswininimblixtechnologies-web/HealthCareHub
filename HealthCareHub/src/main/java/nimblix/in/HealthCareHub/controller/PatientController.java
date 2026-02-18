package nimblix.in.HealthCareHub.controller;


import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        return patientService.create(patient);
    }

    @GetMapping
    public List<Patient> getAll() {
        return patientService.getAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id,
                          @RequestBody Patient patient) {
        return patientService.update(id, patient);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        patientService.delete(id);
        return "Patient deleted successfully";
    }
}



