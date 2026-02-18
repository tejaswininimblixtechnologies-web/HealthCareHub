package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "rooms",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_hospital_room",
                        columnNames = {"hospital_id", "room_number"}
                )
        }
)
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", unique = true, nullable = false)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status;   // AVAILABLE,OCCUPIED,MAINTENANCE


    @Column(name = "bed_count")
    private Integer bedCount;  // Number of beds in the room

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ===== RELATIONSHIPS =====

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;



    // Hooks for timestamps
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}


