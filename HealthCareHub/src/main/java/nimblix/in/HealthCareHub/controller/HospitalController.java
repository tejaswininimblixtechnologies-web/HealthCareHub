package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.HospitalResponse;
import nimblix.in.HealthCareHub.response.RoomResponse;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final SpecializationRepository specializationRepository;

    @PostMapping("/register")
    public HospitalResponse registerHospital(
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
    // Create Specialization (NO DTO – single field only)
    @PostMapping("/createSpecialization")
    public ResponseEntity<?> createSpecialization(
            @RequestParam String name) {

        specializationRepository.findByName(name)
                .ifPresent(existing -> {
                    throw new RuntimeException("Specialization already exists");
                });

        Specialization specialization = new Specialization();
        specialization.setName(name);

        specializationRepository.save(specialization);

        return ResponseEntity.ok("Specialization Created Successfully");
    }
}