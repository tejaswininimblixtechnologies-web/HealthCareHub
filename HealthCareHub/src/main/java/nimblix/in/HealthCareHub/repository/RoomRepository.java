package nimblix.in.HealthCareHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nimblix.in.HealthCareHub.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByRoomNumber(String roomNumber);

}
