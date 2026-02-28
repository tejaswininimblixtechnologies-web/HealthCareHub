package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.*;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // ================= CREATE MEDICAL RECORD =================
    @PostMapping("/medical-record")
    public ResponseEntity<?> createMedicalRecord(
            @RequestBody CreateMedicalRecordRequest request) {

        if (request == null
                || request.getAppointmentId() == null
                || request.getDiagnosis() == null
                || request.getDiagnosis().isBlank()) {

            return ResponseEntity.badRequest()
                    .body("appointmentId and diagnosis are required");
        }

        return ResponseEntity.ok(
                patientService.createMedicalRecord(request)
        );
    }

    // ================= UPDATE DIAGNOSIS =================
    @PutMapping("/medical-record/diagnosis")
    public ResponseEntity<?> updateDiagnosis(
            @RequestBody UpdateDiagnosisRequest request) {

        if (request == null
                || request.getAppointmentId() == null
                || request.getDiagnosis() == null
                || request.getDiagnosis().isBlank()) {

            return ResponseEntity.badRequest()
                    .body("appointmentId and diagnosis are required");
        }

        return ResponseEntity.ok(
                patientService.updateDiagnosis(
                        request.getAppointmentId(),
                        request.getDiagnosis()
                )
        );
    }

    // ================= ADD TREATMENT PLAN =================
    @PutMapping("/medical-record/treatment")
    public ResponseEntity<?> addTreatment(
            @RequestBody TreatmentPlanRequest request) {

        if (request == null
                || request.getAppointmentId() == null
                || request.getTreatmentPlan() == null
                || request.getTreatmentPlan().isBlank()) {

            return ResponseEntity.badRequest()
                    .body("appointmentId and treatmentPlan are required");
        }

        return ResponseEntity.ok(
                patientService.addTreatment(
                        request.getAppointmentId(),
                        request.getTreatmentPlan()
                )
        );
    }

    // ================= ADD CLINICAL NOTES =================
    @PutMapping("/medical-record/notes")
    public ResponseEntity<?> addNotes(
            @RequestBody ClinicalNotesRequest request) {

        if (request == null
                || request.getAppointmentId() == null
                || request.getClinicalNotes() == null
                || request.getClinicalNotes().isBlank()) {

            return ResponseEntity.badRequest()
                    .body("appointmentId and clinicalNotes are required");
        }

        return ResponseEntity.ok(
                patientService.addClinicalNotes(
                        request.getAppointmentId(),
                        request.getClinicalNotes()
                )
        );
    }

    // ================= VIEW MEDICAL TIMELINE =================
    @GetMapping("/{patientId}/medical-timeline")
    public ResponseEntity<?> getTimeline(
            @PathVariable Long patientId) {

        if (patientId == null || patientId <= 0) {
            return ResponseEntity.badRequest()
                    .body("Invalid patientId");
        }

        return ResponseEntity.ok(
                patientService.getMedicalTimeline(patientId)
        );
    }
}