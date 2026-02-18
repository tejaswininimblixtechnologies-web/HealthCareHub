package nimblix.in.HealthCareHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Add new room
    @PostMapping("/add")
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }
}
