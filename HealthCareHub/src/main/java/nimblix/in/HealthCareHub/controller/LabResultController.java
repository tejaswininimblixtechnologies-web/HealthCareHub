package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.LabResultRequest;
import nimblix.in.HealthCareHub.response.LabResultResponse;
import nimblix.in.HealthCareHub.service.LabResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab-results")
@RequiredArgsConstructor
public class LabResultController {

    private final LabResultService labResultService;

    @PostMapping("/upload")
    public LabResultResponse uploadLabResult(@RequestBody LabResultRequest request) {
        return labResultService.uploadLabResult(request);
    }
}