package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.HospitalRequestDTO;
import nimblix.in.HealthCareHub.dto.HospitalResponseDTO;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HospitalController {

    private final HospitalService hospitalService;

    // Create Hospital
    @PostMapping
    public HospitalResponseDTO createHospital(
            @RequestBody HospitalRequestDTO request) {
        return hospitalService.createHospital(request);
    }

    // Get All Hospitals
    @GetMapping
    public List<HospitalResponseDTO> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }
}