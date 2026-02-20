package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

    @Entity
    @Data
    public class MedicalRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long patientId;
        private Long doctorId;

        private String diagnosis;
        private String treatmentPlan;

        private String clinicalNotes;

        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
    }

