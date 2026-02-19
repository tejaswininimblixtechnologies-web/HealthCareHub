package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.MedicalReport;
import nimblix.in.HealthCareHub.service.MedicalReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class MedicalReportController {

    private final MedicalReportService medicalReportService;

    @GetMapping("/patient/{patientId}")
    public List<MedicalReport> getReports(@PathVariable Long patientId) {
        return medicalReportService.getReportsByPatient(patientId);
    }
}

