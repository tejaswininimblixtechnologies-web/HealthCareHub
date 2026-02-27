package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.SpecializationRequest;
import nimblix.in.HealthCareHub.response.HospitalResponse;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final SpecializationRepository specializationRepository;

    // CREATE HOSPITAL
    @PostMapping("/register")
    public HospitalResponse registerHospital(
            @RequestBody HospitalRegistrationRequest request) {

        return hospitalService.registerHospital(request);
    }

    // CREATE SPECIALIZATION
    @PostMapping("/createSpecialization")
    public ResponseEntity<?> createSpecialization(
            @Validated @RequestBody SpecializationRequest request) {

        specializationRepository.findByName(request.getName())
                .ifPresent(existing -> {
                    throw new RuntimeException("Specialization already exists");
                });

        Specialization specialization = new Specialization();
        specialization.setName(request.getName());

        specializationRepository.save(specialization);

        return ResponseEntity.ok("Specialization Created Successfully");
    }
}