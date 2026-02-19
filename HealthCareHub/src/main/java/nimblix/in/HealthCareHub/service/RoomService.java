package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.RoomRequest;
import nimblix.in.HealthCareHub.model.Room;

public interface RoomService {

    Room addRoom(RoomRequest request);

}
