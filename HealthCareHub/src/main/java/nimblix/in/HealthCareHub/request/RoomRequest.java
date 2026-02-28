package nimblix.in.HealthCareHub.request;

import lombok.Data;

@Data
public class RoomRequest {

    private Long hospitalId;

    private String roomNumber;
    private String roomType;
    private int bedCount;
    private double pricePerDay;
    private int floor;
}