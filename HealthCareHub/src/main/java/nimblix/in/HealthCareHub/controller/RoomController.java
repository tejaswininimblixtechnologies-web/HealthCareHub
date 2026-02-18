package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.response.UpdateRoomStatusRequest;
import nimblix.in.HealthCareHub.serviceImpl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals/{hospitalId}/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // CREATE ROOM
    @PostMapping
    public ResponseEntity<Room> createRoom(
            @PathVariable Long hospitalId,
            @RequestBody Room room) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomService.createRoom(hospitalId, room));
    }

    // GET ALL ROOMS OF A HOSPITAL
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(
            @PathVariable Long hospitalId) {

        return ResponseEntity.ok(
                roomService.getRoomsByHospital(hospitalId)
        );
    }

    // GET ROOM BY ROOM NUMBER (Hospital-based)
    @GetMapping("/{roomNumber}")
    public ResponseEntity<Room> getRoom(
            @PathVariable Long hospitalId,
            @PathVariable String roomNumber) {

        return ResponseEntity.ok(
                roomService.getRoomByHospitalAndRoomNumber(hospitalId, roomNumber)
        );
    }

    // UPDATE ROOM STATUS
    @PatchMapping("/{roomNumber}/status")
    public ResponseEntity<Room> updateRoomStatus(
            @PathVariable Long hospitalId,
            @PathVariable String roomNumber,
            @RequestBody UpdateRoomStatusRequest request) {

        return ResponseEntity.ok(
                roomService.updateRoomStatus(hospitalId, roomNumber, request.getStatus())
        );
    }

    // DELETE ROOM
    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable Long hospitalId,
            @PathVariable String roomNumber) {

        roomService.deleteRoom(hospitalId, roomNumber);
        return ResponseEntity.noContent().build();
    }
}
