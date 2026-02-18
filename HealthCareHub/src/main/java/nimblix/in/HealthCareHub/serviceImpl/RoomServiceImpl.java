package nimblix.in.HealthCareHub.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.repository.RoomRepository;
import nimblix.in.HealthCareHub.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room addRoom(Room room) {

        // check duplicate room number
        if (roomRepository.existsByRoomNumber(room.getRoomNumber())) {
            throw new RuntimeException("Room already exists with this number");
        }

        // default availability
        room.setAvailability("AVAILABLE");

        return roomRepository.save(room);
    }
}
