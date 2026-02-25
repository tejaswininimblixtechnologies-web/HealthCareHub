package nimblix.in.HealthCareHub.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;

@Entity
@Table(name = "lab_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LabResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testName;
    private String resultValue;
    private String status;

    private String uploadedAt;
    private String createdTime;
    private String updatedTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @PrePersist
    protected void onCreate(){
        uploadedAt = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        createdTime = uploadedAt;
        updatedTime = uploadedAt;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedTime = HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }
}