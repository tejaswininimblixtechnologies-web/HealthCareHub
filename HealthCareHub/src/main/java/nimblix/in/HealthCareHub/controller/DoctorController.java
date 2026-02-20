package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.DoctorAvailability;
import nimblix.in.HealthCareHub.request.DoctorAvailabilityRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor

public class DoctorController {
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


    /*
Json object:
key and value pair

{
"name": "tejaswini",
"mobile number":"8937483454",
"date":"10-05-2026",
}

*/

    @PostMapping("/register")
    public String registerDoctor(@RequestBody DoctorRegistrationRequest doctorRegistrationRequest) {
        return doctorService.RegisterDoctor(doctorRegistrationRequest);
    }






    private final DoctorService doctorService;

    @PostMapping("/timeslot/add")
    public ResponseEntity<?> addTimeSlot(@RequestBody DoctorAvailabilityRequest request) {
        DoctorAvailability saved = doctorService.addDoctorTimeSlot(request);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("/timeslot/update/{slotId}")
    public ResponseEntity<?> updateTimeSlot(
            @PathVariable Long slotId,
            @RequestBody DoctorAvailabilityRequest request) {
        DoctorAvailability updated = doctorService.updateDoctorTimeSlot(slotId, request);
        return ResponseEntity.ok(updated);
    }
}
