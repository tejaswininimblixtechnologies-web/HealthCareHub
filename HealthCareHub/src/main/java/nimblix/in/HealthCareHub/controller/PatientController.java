package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.LabResultRequest;
import nimblix.in.HealthCareHub.response.LabResultResponse;
import nimblix.in.HealthCareHub.service.LabResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final LabResultService labResultService;

    @PostMapping("/{patientId}/lab-results")
    public LabResultResponse uploadLabResult(
            @PathVariable Long patientId,
            @RequestBody LabResultRequest request) {

        return labResultService.uploadLabResult(patientId, request);
    }

    @GetMapping("/{patientId}/lab-results")
    public List<LabResultResponse> getLabResults(@PathVariable Long patientId) {
        return labResultService.getLabResultsByPatientId(patientId);
    }
}