package nimblix.in.HealthCareHub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor

public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/register")
    public String registerDoctor(@RequestBody DoctorRegistrationRequest request) {
        return doctorService.registerDoctor(request);

    }

    @GetMapping("/getDoctorDetails/{doctorId}/{hospitalId}")
    public ResponseEntity<?> getDoctorDetails(@PathVariable Long doctorId,
                                              @PathVariable Long hospitalId) {
        return doctorService.getDoctorDetails(doctorId, hospitalId);
    }

    @PutMapping("/updateDoctorDetails")
     public String updateDoctorDetails(@RequestBody DoctorRegistrationRequest request){
        return doctorService.updateDoctorDetails(request);
    }


    @DeleteMapping("/deleteDoctorDetails")
    public String deleteDoctorDetails(@RequestParam Long doctorId){
        return doctorService.deleteDoctorDetails(doctorId);
    }


    @PutMapping("/update/{doctorId}")
    public ResponseEntity<?> updateDoctor(
            @PathVariable Long doctorId,
            @Valid @RequestBody Doctor request) {

        return doctorService.updateDoctor(doctorId, request);
    }






}
