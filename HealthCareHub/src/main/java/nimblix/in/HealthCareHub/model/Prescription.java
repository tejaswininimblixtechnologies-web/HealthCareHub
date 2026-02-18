package nimblix.in.HealthCareHub.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private patient patient;

     //@ManyToOne
     //@JoinColumn(name = "doctor_id")
     //private Doctor doctor;

    @Column(nullable = false)
    private String medicationName;

    private String DoctorName;

    private String dosage;

    private String frequency;

    private String duration;

    @Column(length = 500)
    private String instructions;

    @Column(nullable = false)
    private LocalDateTime prescriptionDate;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
