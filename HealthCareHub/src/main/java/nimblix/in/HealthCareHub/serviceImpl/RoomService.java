package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.exception.RoomNotFoundException;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.model.RoomStatus;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    // CREATE ROOM
    public Room createRoom(Long hospitalId, Room room) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        room.setHospital(hospital);
        room.setStatus(RoomStatus.AVAILABLE);

        return roomRepository.save(room);
    }

    // GET ALL ROOMS BY HOSPITAL
    public List<Room> getRoomsByHospital(Long hospitalId) {
        return roomRepository.findByHospitalId(hospitalId);
    }

    // GET ROOM BY HOSPITAL + ROOM NUMBER
    public Room getRoomByHospitalAndRoomNumber(Long hospitalId, String roomNumber) {

        return roomRepository
                .findByHospitalIdAndRoomNumber(hospitalId, roomNumber)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));
    }

    // UPDATE ROOM STATUS
    public Room updateRoomStatus(Long hospitalId, String roomNumber, RoomStatus status) {

        Room room = getRoomByHospitalAndRoomNumber(hospitalId, roomNumber);
        room.setStatus(status);

        return roomRepository.save(room);
    }

    // DELETE ROOM
    public void deleteRoom(Long hospitalId, String roomNumber) {

        Room room = getRoomByHospitalAndRoomNumber(hospitalId, roomNumber);
        roomRepository.delete(room);
    }
}
