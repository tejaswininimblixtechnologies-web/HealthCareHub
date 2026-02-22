package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "phoneNo")
    private String phone;

    @Column(name = "qualification")
    private String qualification;
    @Column(name = "status")
    private String status;

    // Login User (Doctor Account)
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Many Doctors → One Hospital
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;


    @PrePersist
    protected void onCreate(){
        createdTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();

        if (status == null) {
            status = HealthCareConstants.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedTime= HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();


    }

}
