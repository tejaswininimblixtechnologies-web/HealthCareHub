package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_documents")
public class PatientDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // original filename
    private String fileName;

    // file type (pdf, jpg, png)
    private String fileType;

    // stored path or filename
    private String filePath;

    // VERY IMPORTANT RELATION
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", nullable = false)
    private Patient patient;

    // getters setters

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setFileSize(long size) {
    }

    public void setUploadedAt(LocalDateTime now) {
    }
}