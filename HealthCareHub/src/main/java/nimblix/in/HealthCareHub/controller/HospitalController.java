package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.UpdateRoomStatusRequest;
import nimblix.in.HealthCareHub.response.UpdateRoomStatusResponse;
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
    public String registerHospital(@RequestBody HospitalRegistrationRequest request) {
        return hospitalService.registerHospital(request);
    }
    // Update Room Status
    @PatchMapping("/{hospitalId}/room-status")
    public ResponseEntity<UpdateRoomStatusResponse> updateRoomStatus(
            @PathVariable Long hospitalId,
            @RequestBody UpdateRoomStatusRequest request) {

        UpdateRoomStatusResponse response = hospitalService.updateRoomStatus(hospitalId,request);

        return ResponseEntity.ok(response);
    }
}
