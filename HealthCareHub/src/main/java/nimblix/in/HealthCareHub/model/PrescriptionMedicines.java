package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionMedicines {
    @Id
    @Column(name = "PrescriptionMedicine_id", length = 50)
    private String id;

    @Column(name = "Medicine_Name", nullable = false, length = 150)
    private String medicineName;

    @Column(name = "Dosage", nullable = false, length = 100)
    private String dosage;

    @ManyToOne
    @JoinColumn(name = "Prescription_id", nullable = false)
    private Prescription prescription;
}
