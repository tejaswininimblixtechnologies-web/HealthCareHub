package nimblix.in.HealthCareHub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    // 🔹 Step 3: Duplicate room number check
    boolean existsByRoomNumber(String roomNumber);

    // 🔹 Find room by room number
    Optional<Room> findByRoomNumber(String roomNumber);

    // 🔹 Get all available rooms (true = available, false = occupied)
    List<Room> findByAvailability(Boolean availability);
}