package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.AdmissionHistoryResponse;
import nimblix.in.HealthCareHub.service.AdmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admissions")
@RequiredArgsConstructor
public class AdmissionController {

    private final AdmissionService admissionService;

    @GetMapping("/history/{patientId}")
    public ResponseEntity<List<AdmissionHistoryResponse>> getAdmissionHistory(
            @PathVariable Long patientId) {

        List<AdmissionHistoryResponse> history =
                admissionService.getAdmissionHistory(patientId);

        return ResponseEntity.ok(history);
    }
}