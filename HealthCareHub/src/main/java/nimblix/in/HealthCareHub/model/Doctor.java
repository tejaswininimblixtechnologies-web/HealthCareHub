package nimblix.in.HealthCareHub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;

import java.util.List;

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

    @Column(name = "phoneNo", unique = false)
    private String phone;

    @Column(name = "EmailId")
    private String EmailId;

    @Column(name = "password")
    private String password;

    @Column(name = "qualification")
    private String qualification;

    // Login User (Doctor Account)
     @Column(name = "user_id")
    private Long userId;

    // Many Doctors → One Hospital

    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column(name = "specialization_id")
    private Long specializationId;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

    @Column(name = "consultation_fee")
    private Double consultationFee;

    @PrePersist
    protected void onCreate(){
        createdTime= HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime= HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();

    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedTime= HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();


    }

}
