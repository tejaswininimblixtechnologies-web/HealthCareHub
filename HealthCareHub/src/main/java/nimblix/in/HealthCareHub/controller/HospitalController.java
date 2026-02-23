package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<List<Hospital>> getHospitals(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "minBeds", required = false) Integer minBeds) {

        List<Hospital> hospitals =
                hospitalService.searchAndFilter(name, city, state, minBeds);

        return ResponseEntity.ok(hospitals);
    }
}