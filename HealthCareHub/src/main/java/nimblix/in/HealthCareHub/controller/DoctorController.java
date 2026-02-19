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
