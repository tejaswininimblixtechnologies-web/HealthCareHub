
package nimblix.in.HealthCareHub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nimblix.in.HealthCareHub.dto.HospitalDropdownDTO;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.service.HospitalService;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<String> saveHospital(@RequestBody Hospital hospital) {
        return ResponseEntity.ok(hospitalService.saveHospital(hospital));
    }

    @GetMapping("/list")
    public ResponseEntity<List<HospitalDropdownDTO>> getHospitalList() {
        return ResponseEntity.ok(hospitalService.getHospitalList());
    }
}