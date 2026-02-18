package nimblix.in.HealthCareHub.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String admissionDate;   // String as required

    private String dischargeDate;   // String as required

    private String diagnosis;

    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
