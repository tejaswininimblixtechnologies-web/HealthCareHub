package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long doctorId;

    private Long patientId;

    private String medicine;

    private String dosage;
}
