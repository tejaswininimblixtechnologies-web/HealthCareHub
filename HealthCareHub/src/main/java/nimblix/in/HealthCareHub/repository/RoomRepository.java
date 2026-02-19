package nimblix.in.HealthCareHub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    // duplicate room number check
    boolean existsByRoomNumber(String roomNumber);

    // find room by room number
    Optional<Room> findByRoomNumber(String roomNumber);

    // get all available rooms
    List<Room> findByAvailability(String availability);

}
