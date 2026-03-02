package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class UpdateRoomStatusRequest {

        private String roomNumber;
        private String roomType;
        private String roomStatus;
}
