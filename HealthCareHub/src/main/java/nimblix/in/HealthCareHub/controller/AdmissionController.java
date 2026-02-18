package nimblix.in.HealthCareHub.controller;


import nimblix.in.HealthCareHub.model.Admission;
import nimblix.in.HealthCareHub.service.AdmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService admissionService;

    @PostMapping("/{patientId}")
    public Admission create(@PathVariable Long patientId,
                            @RequestBody Admission admission) {
        return admissionService.create(patientId, admission);
    }

    @GetMapping
    public List<Admission> getAll() {
        return admissionService.getAll();
    }

    @GetMapping("/{id}")
    public Admission getById(@PathVariable Long id) {
        return admissionService.getById(id);
    }

    @PutMapping("/{id}")
    public Admission update(@PathVariable Long id,
                            @RequestBody Admission admission) {
        return admissionService.update(id, admission);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        admissionService.delete(id);
        return "Admission deleted successfully";
    }

    // 🎯 MAIN FEATURE
    @GetMapping("/history/{patientId}")
    public List<Admission> getHistory(@PathVariable Long patientId) {
        return admissionService.getHistory(patientId);
    }
}


