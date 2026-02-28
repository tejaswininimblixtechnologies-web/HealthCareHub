package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.RoomRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    // REGISTER HOSPITAL
    @PostMapping("/register")
    public ResponseEntity<String> registerHospital(
            @RequestBody HospitalRegistrationRequest request) {

        String response = hospitalService.registerHospital(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @PostMapping("/add-room")
    public ResponseEntity<String> addRoom(
            @RequestBody RoomRequest request) {

        hospitalService.addRoom(request);

        return new ResponseEntity<>("Room added successfully", HttpStatus.CREATED);
    }
}