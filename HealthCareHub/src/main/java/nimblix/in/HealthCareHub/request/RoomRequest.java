package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {
    private String roomNumber;
    private String roomStatus;
    private String roomType;
}
