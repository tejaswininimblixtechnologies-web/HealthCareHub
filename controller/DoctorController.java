package nimblix.in.HealthCareHub.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorListResponse;
import nimblix.in.HealthCareHub.response.DoctorProfileResponse;
import nimblix.in.HealthCareHub.response.DoctorRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nimblix.in.HealthCareHub.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Add a new doctor under hospital
    @PostMapping("/addDoctor")
    public ResponseEntity<DoctorProfileResponse> addDoctor(
            @Valid @RequestBody DoctorRegistrationRequest request) {

        DoctorProfileResponse response = doctorService.addDoctor(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<String>> getAllRoles() {

        return ResponseEntity.ok(doctorService.getAllRoles());
    }

    //Filter doctors based on their specialization such as Orthopedics, Pulmonology, etc
    @GetMapping("/filter")
    public ResponseEntity<Map<String, Object>> filterDoctorsBySpecialization(
            @RequestParam(required = false) String specialization) {

        if (specialization == null || specialization.trim().isEmpty()) {

            Map<String, Object> error = new HashMap<>();
            error.put(HealthCareConstants.STATUS, HttpStatus.BAD_REQUEST.value());
            error.put(HealthCareConstants.MESSAGE, "Specialization parameter is required");

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        List<DoctorProfileResponse> doctors =
                doctorService.filterDoctorsBySpecialization(specialization);

        if (doctors == null || doctors.isEmpty()) {

            Map<String, Object> error = new HashMap<>();
            error.put(HealthCareConstants.STATUS, HttpStatus.NOT_FOUND.value());
            error.put(HealthCareConstants.MESSAGE, "No doctors found with specialization: " + specialization);

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        Map<String, Object> response = new HashMap<>();
        response.put(HealthCareConstants.STATUS, HttpStatus.OK.value());
        response.put(HealthCareConstants.MESSAGE, HealthCareConstants.DOCTORS_FETCHED_SUCCESSFULLY);
        response.put(HealthCareConstants.COUNT, doctors.size());
        response.put(HealthCareConstants.DATA, doctors);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    //Retrieve the list of doctors associated with a specific hospital.
    @GetMapping("/hospitals/{hospitalId}/doctors")
    public ResponseEntity<DoctorListResponse> getDoctorsByHospital(@PathVariable Long hospitalId) {

        DoctorListResponse response = doctorService.getDoctorsByHospital(hospitalId);

        return ResponseEntity.ok(response);
    }



}
