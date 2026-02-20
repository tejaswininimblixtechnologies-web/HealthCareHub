package nimblix.in.HealthCareHub.response;

import lombok.Data;
import nimblix.in.HealthCareHub.model.RoomStatus;

@Data
public class UpdateRoomStatusRequest {
    private RoomStatus status;
}
