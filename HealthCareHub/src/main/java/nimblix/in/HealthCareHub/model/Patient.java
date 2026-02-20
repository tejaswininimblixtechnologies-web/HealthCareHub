package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    private String disease;
    private Integer age;




//    @ManyToMany
//    @JoinTable(
//            name="patient_medicines",
//            joinColumns=@JoinColumn(name="patient_id"),
//            inverseJoinColumns = @JoinColumn(name="medicine_id")
//    )
//    private List<Medicine> medicines;
}
