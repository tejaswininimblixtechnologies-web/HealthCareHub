package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.service.LabResultService;

@RestController
@RequestMapping("/api/lab-results")
@RequiredArgsConstructor
public class LabResultController {

    private final LabResultService labResultService;

    @PostMapping("/upload")
    public LabResult uploadLabResult(@RequestBody LabResult labResult) {
        return labResultService.uploadLabResult(labResult);
    }
}
