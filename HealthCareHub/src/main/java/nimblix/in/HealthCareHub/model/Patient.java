package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;
@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    public void setDeleted() {
        this.isDeleted = true;
    }


    @Column(name = "name")
    private String name;
    private Integer age;
    private String gender;

    @Column(name = "phoneNo")
    private String phoneNo;

    @Column(name = "disease")
    private String disease;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    private String createdTime;
    private String updatedTime;

    @PrePersist
    protected void onCreate() {
        createdTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();

    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();


    }


}
