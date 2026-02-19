package nimblix.in.HealthCareHub.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nimblix.in.HealthCareHub.dto.RoomRequest;
import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.service.RoomService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth") // 🔐 This enables Swagger lock authorization
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<Room> addRoom(@Valid @RequestBody RoomRequest request) {
        Room savedRoom = roomService.addRoom(request);
        return ResponseEntity.ok(savedRoom);
    }
}
