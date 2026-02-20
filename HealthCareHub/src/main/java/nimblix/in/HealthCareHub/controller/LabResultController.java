package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.service.LabResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab")
@RequiredArgsConstructor
public class LabResultController {

    private final LabResultService labResultService;

    // Create lab result (this will also send mail if READY)
    @PostMapping
    public ResponseEntity<LabResult> create(@RequestBody LabResult labResult) {

        LabResult saved = labResultService.createLabResult(labResult);

        return ResponseEntity.ok(saved);
    }

    // Mark result as ready and notify patient
    @PostMapping("/{id}/ready")
    public ResponseEntity<String> markReady(@PathVariable Long id) {

        labResultService.notifyPatient(id);

        return ResponseEntity.ok("Patient notified successfully!");
    }
}
