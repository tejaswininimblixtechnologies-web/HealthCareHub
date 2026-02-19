package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.dto.RoomRequest;
import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.repository.RoomRepository;
import nimblix.in.HealthCareHub.service.RoomService;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room addRoom(RoomRequest request) {

        // 1️⃣ Check duplicate room number
        if (roomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new RuntimeException("Room already exists with this number");
        }

        // 2️⃣ Convert DTO → Entity
        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setRoomType(request.getRoomType());
        room.setPricePerDay(request.getPricePerDay());

        // 3️⃣ Default value set by system
        room.setAvailability("AVAILABLE");

        // 4️⃣ Save to DB
        return roomRepository.save(room);
    }
}
