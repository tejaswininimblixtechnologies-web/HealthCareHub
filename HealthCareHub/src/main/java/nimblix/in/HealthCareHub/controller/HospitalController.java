package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.HospitalResponse;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HospitalController {

    private final HospitalService hospitalService;
    private final SpecializationRepository specializationRepository;

    // Create Hospital
    @PostMapping
    public HospitalResponse createHospital(
            @RequestBody HospitalRegistrationRequest request) {

        return hospitalService.createHospital(request);
    }

    // Get All Hospitals
    @GetMapping
    public List<HospitalResponse> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }

    // SPECIALIZATION APIs (ADDED)
    // Create Specialization
    @PostMapping("/specializations")
    public Specialization createSpecialization(
            @RequestBody Specialization specialization) {

        return specializationRepository.save(specialization);
    }

    // Get All Specializations
    @GetMapping("/specializations")
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }
}