package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;


    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(
            @Valid @RequestBody DoctorRegisterRequest request) {

        return ResponseEntity.ok(doctorService.registerDoctor(request));
    }

    @PostMapping("/register")
    public String registerDoctor(@RequestBody DoctorRegistrationRequest doctorRegistrationRequest) {
        return doctorService.RegisterDoctor(doctorRegistrationRequest);
    }






}

