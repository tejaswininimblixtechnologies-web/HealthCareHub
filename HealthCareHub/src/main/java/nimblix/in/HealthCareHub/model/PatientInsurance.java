package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PatientInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private InsuranceProvider provider;

    private String policyNumber;
    private LocalDate validTill;
}