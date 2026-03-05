package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorProfileResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import nimblix.in.HealthCareHub.serviceImpl.DoctorServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {


    private final DoctorService doctorService;


    private final DoctorServiceImpl doctorServiceImpl;


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



    @PostMapping("/specialization/create")
    public Specialization createSpecialization(
            @RequestBody Specialization specialization) {
        return doctorServiceImpl.createSpecialization(specialization);
    }

    @GetMapping("/specializations")
    public List<Specialization> getAllSpecializations() {
        return doctorServiceImpl.getAllSpecializations();
    }

    @PutMapping("/specialization/update/{id}")
    public Specialization updateSpecialization(
            @PathVariable Long id,
            @RequestBody Specialization specialization) {
        return doctorServiceImpl.updateSpecialization(id, specialization);
    }

    @DeleteMapping("/specialization/delete/{id}")
    public String deleteSpecialization(@PathVariable Long id) {
        return doctorServiceImpl.deleteSpecialization(id);
    }
}