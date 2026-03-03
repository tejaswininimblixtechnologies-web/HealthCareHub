package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.MedicineRequest;
import nimblix.in.HealthCareHub.response.MedicineResponse;
import nimblix.in.HealthCareHub.service.HospitalService;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final MedicineService medicineService;

    @PostMapping("/register")
    public String registerHospital(@RequestBody HospitalRegistrationRequest request) {
        return hospitalService.registerHospital(request);
    }

    // Add Medicine to hospital
    @PostMapping("/{hospitalId}/medicine")
    public ResponseEntity<String> addMedicine(
            @PathVariable Long hospitalId,
            @RequestBody MedicineRequest request) {

        return ResponseEntity.ok(
                medicineService.addMedicine(hospitalId, request));
    }

    // Get Medicines of hospital
    @GetMapping("/{hospitalId}/medicines")
    public ResponseEntity<List<MedicineResponse>> getMedicines(
            @PathVariable Long hospitalId) {

        return ResponseEntity.ok(
                medicineService.getHospitalMedicines(hospitalId));
    }

    
}
