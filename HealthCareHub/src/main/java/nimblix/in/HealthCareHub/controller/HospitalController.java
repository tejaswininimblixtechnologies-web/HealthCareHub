package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    // ---------------- REGISTER HOSPITAL ----------------
    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> registerHospital(
            @RequestBody HospitalRegistrationRequest request) {

        String response = hospitalService.registerHospital(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // ---------------- ADD ROOM ----------------
    @PostMapping(
            value = "/{hospitalId}/add-room",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Room> addRoom(
            @PathVariable Long hospitalId,
            @RequestBody Room room) {

        Room savedRoom = hospitalService.addRoom(hospitalId, room);

        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }
}