package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/")
    public ResponseEntity<List<DoctorResponse>> getAllDoctors(
            @RequestParam(required = false) Long specializationId,
            @RequestParam(required = false) Long hospitalId,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String name) {
        List<DoctorResponse> doctors = doctorService.getAllDoctors(specializationId, hospitalId, city, name);
        return ResponseEntity.ok(doctors);
    }

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


    @GetMapping("/{doctorId}/profile")
    public ResponseEntity<Map<String, Object>> getDoctorProfile(
            @PathVariable Long doctorId) {

        DoctorProfileResponse response = doctorService.getDoctorProfile(doctorId);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "Doctor profile fetched successfully");
        result.put("data", response);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/deleteDoctorDetails")
    public String deleteDoctorDetails(@RequestParam Long doctorId){
        return doctorService.deleteDoctorDetails(doctorId);
    }


}
