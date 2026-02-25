package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_documents")

@Getter
@Setter
@NoArgsConstructor
public class PatientDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many documents belong to one patient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patient;

    // original uploaded file name
    @Column(name = "file_name", nullable = false)
    private String fileName;

    // stored location in server
    @Column(name = "file_path", nullable = false)
    private String filePath;

    // Prescription / Lab Report / Scan etc
    @Column(name = "document_type", nullable = false)
    private String documentType;

    // optional user note
    @Column(name = "description")
    private String description;

    // upload timestamp
    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;
}