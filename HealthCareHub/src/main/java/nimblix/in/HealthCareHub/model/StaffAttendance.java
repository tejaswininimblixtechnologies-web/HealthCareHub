package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "staff_attendance")
public class StaffAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long staffId;

    private LocalDate date;

    private String status;  // Present / Absent / Leave

    private LocalTime checkIn;

    private LocalTime checkOut;

    // Getters and Setters
}