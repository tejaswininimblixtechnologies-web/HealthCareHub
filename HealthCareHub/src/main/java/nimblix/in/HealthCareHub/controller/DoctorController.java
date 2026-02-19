package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.dto.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {


    /*
Json object:
key and value pair
{
"name": "tejaswini",
"mobile number":"8937483454",
"date":"10-05-2026",
}
*/


    @Autowired
    private DoctorService doctorService;

    // ⭐ Register Doctor
    @PostMapping("/register")
    public String registerDoctor(@RequestBody DoctorRegistrationRequest request) {
        return doctorService.registerDoctor(request);
    }

    // ⭐ View appointments
    @GetMapping("/appointments")
    public String doctorAppointments() {
        return "Doctor appointments list";
    }

    // ⭐ Update availability
    @PutMapping("/availability")
    public String updateAvailability() {
        return "Doctor availability updated";
    }
}