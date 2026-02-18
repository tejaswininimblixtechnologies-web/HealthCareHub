package nimblix.in.HealthCareHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;

import java.util.List;

@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String phone;

    private String email;

    private Integer totalBeds;

    // One Hospital → Many Doctors
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Doctor> doctors;

    // One Hospital → Many Patients
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Patient> patients;

    @Column(name = "created_time", updatable = false)
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

    @PrePersist
    protected void onCreate() {
        createdTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = createdTime;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }
}
