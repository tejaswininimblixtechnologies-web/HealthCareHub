package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.ClinicalNotesRequest;
import nimblix.in.HealthCareHub.request.CreateMedicalRecordRequest;
import nimblix.in.HealthCareHub.request.TreatmentPlanRequest;
import nimblix.in.HealthCareHub.request.UpdateDiagnosisRequest;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // =====================================================
    // ✅ CREATE MEDICAL RECORD (ADD DIAGNOSIS)
    // =====================================================
    @PostMapping("/medical-record")
    public ResponseEntity<?> createMedicalRecord(
            @RequestBody CreateMedicalRecordRequest request) {

        return ResponseEntity.ok(
                patientService.createMedicalRecord(request));
    }

    // =====================================================
    // ✅ UPDATE DIAGNOSIS
    // =====================================================
    @PutMapping("/medical-record/{id}/diagnosis")
    public ResponseEntity<?> updateDiagnosis(
            @PathVariable Long id,
            @RequestBody UpdateDiagnosisRequest request) {

        return ResponseEntity.ok(
                patientService.updateDiagnosis(id, request.getDiagnosis()));
    }

    // =====================================================
    // ✅ ADD TREATMENT PLAN
    // =====================================================
    @PutMapping("/medical-record/{id}/treatment")
    public ResponseEntity<?> addTreatment(
            @PathVariable Long id,
            @RequestBody TreatmentPlanRequest request) {

        return ResponseEntity.ok(
                patientService.addTreatment(id, request.getTreatmentPlan()));
    }

    // =====================================================
    // ✅ ADD CLINICAL NOTES
    // =====================================================
    @PutMapping("/medical-record/{id}/notes")
    public ResponseEntity<?> addNotes(
            @PathVariable Long id,
            @RequestBody ClinicalNotesRequest request) {

        return ResponseEntity.ok(
                patientService.addClinicalNotes(id, request.getClinicalNotes()));
    }

    // =====================================================
    // ✅ VIEW MEDICAL TIMELINE
    // =====================================================
    @GetMapping("/{patientId}/medical-timeline")
    public ResponseEntity<?> getTimeline(
            @PathVariable Long patientId) {

        return ResponseEntity.ok(
                patientService.getMedicalTimeline(patientId));
    }
}