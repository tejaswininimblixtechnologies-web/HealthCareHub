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
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String emailId;
    private String password;
    private Long hospitalId;
    private Long specializationId;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Medicine> medicines;

}
