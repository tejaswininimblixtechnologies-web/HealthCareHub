package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    private String roomType;
    private int bedCount;
    private double pricePerDay;
    private String availability;
    private int floor;
}
