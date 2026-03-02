package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Staff;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.RoomResponse;
import nimblix.in.HealthCareHub.service.HospitalService;
import nimblix.in.HealthCareHub.service.StaffService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final StaffService staffService;
    private final HospitalService hospitalService;

    @PostMapping("/register")
    public String registerHospital(
            @RequestBody HospitalRegistrationRequest request) {

        return hospitalService.registerHospital(request);
    }


    @PostMapping("/{hospitalId}/rooms")
    public String addRooms(
            @PathVariable Long hospitalId,
            @RequestBody List<HospitalRegistrationRequest.Room> rooms) {

        hospitalService.addRooms(hospitalId, rooms);
        return "Rooms added successfully";
    }

    @GetMapping("/{hospitalId}/available-rooms")
    public List<RoomResponse> getAvailableRooms(
            @PathVariable Long hospitalId) {

        return hospitalService.getAvailableRooms(hospitalId);
    }
    @PostMapping("/staff")
    public ResponseEntity<?> addStaff(@RequestBody Staff staff) {

        if (staff == null) {
            return ResponseEntity.badRequest().body("Staff data cannot be null");
        }

        if (staff.getName() == null || staff.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Staff name is required");
        }

        try {
            Staff savedStaff = staffService.addStaff(staff);
            return ResponseEntity.status(201).body(savedStaff);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Unable to add staff");
        }
    }
}




