package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= RELATIONS =================

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // ================= APPOINTMENT DETAILS =================

    private String appointmentDateTime;

    // BOOKED, CANCELLED, COMPLETED
    private String status;

    private String createdTime;
    private String updatedTime;

    // =========================================================
    // ✅ MEDICAL RECORD FIELDS (NEW - FOR TASK 203)
    // =========================================================

    @Column(length = 1000)
    private String diagnosis;

    @Column(length = 2000)
    private String treatmentPlan;

    @Column(length = 3000)
    private String clinicalNotes;

    // ================= TIMESTAMP HANDLING =================

    @PrePersist
    protected void onCreate() {
        createdTime =
                HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
        updatedTime = createdTime;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime =
                HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }
}