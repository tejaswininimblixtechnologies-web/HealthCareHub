package nimblix.in.HealthCareHub.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.request.AdmissionRequest;
import nimblix.in.HealthCareHub.response.AdmissionResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService admissionService;

    @GetMapping("/admission-history")
    public ResponseEntity<Map<String, Object>> getAdmissionHistory(
            @RequestParam Long patientId){

        AdmissionRequest request = new AdmissionRequest();
        request.setPatientId(patientId);
        List<AdmissionResponse> data = admissionService.getAdmissionHistory(request);

        // If patient not found or no admissions
        if (data == null || data.isEmpty()) {

            Map<String, Object> error = new HashMap<>();
            error.put(HealthCareConstants.STATUS, HttpStatus.NOT_FOUND.value());
            error.put(HealthCareConstants.MESSAGE, HealthCareConstants.NO_ADMISSION_HISTORY_FOUND_FOR_PATIENT_ID + request.getPatientId());

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        // Success response
        Map<String, Object> response = new HashMap<>();
        response.put(HealthCareConstants.STATUS, HttpStatus.OK.value());
        response.put(HealthCareConstants.MESSAGE,HealthCareConstants.ADMISSION_HISTORY_FETCH_SUCCESSFULLY );
        response.put(HealthCareConstants.DATA,data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}