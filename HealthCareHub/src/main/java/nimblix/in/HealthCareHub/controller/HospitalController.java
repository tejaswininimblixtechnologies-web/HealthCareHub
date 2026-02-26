package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/register")
    public ResponseEntity<String> registerHospital(
            @RequestBody HospitalRegistrationRequest request) {
        return ResponseEntity.ok(hospitalService.registerHospital(request));
    }

    /* ---------- GET AVAILABLE ROOMS ---------- */

    @GetMapping("/available-rooms/{hospitalId}")
    public ResponseEntity<List<Hospital.RoomInfo>> getAvailableRooms(
            @PathVariable Long hospitalId) {

        List<Hospital.RoomInfo> availableRooms =
                hospitalService.getAvailableRooms(hospitalId);

        if (availableRooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(availableRooms);
    }
}


