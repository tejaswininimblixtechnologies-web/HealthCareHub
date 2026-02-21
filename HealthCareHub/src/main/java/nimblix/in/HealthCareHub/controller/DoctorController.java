package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.service.DoctorService;
import nimblix.in.HealthCareHub.service.RoomService;
import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final RoomService roomService;

    /*
     Json object:
     key and value pair
     {
        "name": "tejaswini",
        "mobile_number": "8937483454",
        "date": "10-05-2026"
     }
    */

    // -----------------------------
    // Doctor Registration API
    // -----------------------------
    @PostMapping("/register")
    public String registerDoctor(
            @RequestBody DoctorRegistrationRequest doctorRegistrationRequest) {

        return doctorService.RegisterDoctor(doctorRegistrationRequest);
    }

    // -----------------------------
    // Get Available Rooms API
    // -----------------------------
    @GetMapping("/available-rooms")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }
}