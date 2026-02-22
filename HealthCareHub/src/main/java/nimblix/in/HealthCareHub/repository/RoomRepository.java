package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailableTrue();
}
