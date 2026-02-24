package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicine;

    @ManyToOne
    private Doctor doctor;
}
