package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    // Create Hospital
    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    // Get All Hospitals
    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }
}
