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

    // Login User

    @Column(name = "user_id")
    private Long userId;

    // Hospital Relationship

    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;


    @PrePersist
    protected void onCreate(){
        createdTime= HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime= HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();

    }



//    @ManyToMany
//    @JoinTable(
//            name="patient_medicines",
//            joinColumns=@JoinColumn(name="patient_id"),
//            inverseJoinColumns = @JoinColumn(name="medicine_id")
//    )
//    private List<Medicine> medicines;
}
