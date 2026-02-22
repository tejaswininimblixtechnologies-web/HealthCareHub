package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Room;
import org.springframework.stereotype.Service;
import java.util.List;

import nimblix.in.HealthCareHub.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailableTrue();
    }
}
