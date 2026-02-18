package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

    private String address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;
}
