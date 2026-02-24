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

    @Column(nullable = false)
    private String name;

    private Integer experienceYears;

    private String phone;

    @Column(unique = true)
    private String emailId;

    private String password;

    private String qualification;

    // ✅ Doctor login account
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ✅ Many Doctors → One Hospital
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;

    // ✅ Many Doctors → One Specialization
    @ManyToOne
    @JoinColumn(name = "specialization_id", nullable = false)
    private Specialization specialization;

    private String createdTime;
    private String updatedTime;

    @PrePersist
    protected void onCreate(){
        createdTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = createdTime;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }
}
