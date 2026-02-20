package nimblix.in.HealthCareHub.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }
}
