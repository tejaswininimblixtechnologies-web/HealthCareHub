package nimblix.in.HealthCareHub.model;


import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private String roomType;
    private boolean available;

    // getters and setters
}