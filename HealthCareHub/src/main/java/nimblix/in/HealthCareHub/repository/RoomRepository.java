package nimblix.in.HealthCareHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import nimblix.in.HealthCareHub.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailableTrue();
}
