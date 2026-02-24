package nimblix.in.HealthCareHub.model;


import jakarta.persistence.*;
import lombok.*;
import nimblix.in.HealthCareHub.utility.HealthCareUtil;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "prescription_id", length = 50)
        private Long id;

        @Column(name = "prescription_date", nullable = false)
        private String date;

        @Column(name = "Patient Name", length = 100)
        private String patientName;

        @Column(name = "Doctor Name", length = 100)
        private String doctorName;

        @Column(name = "Diagnosis", length = 500)
        private String diagnosis;

        @ManyToOne
        @JoinColumn(name = "Patient id", nullable = false)
        private Patient patient;

        @ManyToOne
        @JoinColumn(name = "Doctor id", nullable = false)
        private Doctor doctor;

        @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
        private List<PrescriptionMedicines> medicines;

    @PrePersist
    protected void onCreate(){
        date= HealthCareUtil.changeCurrentTimeToLocalDateFromGmtToISTInString();
    }
}
