package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PatientDocumentUploadRequest {

    // which patient uploading document
    private Long patientId;

    // type of document (Prescription, Scan, LabReport etc.)
    private String documentType;

    // optional note by user
    private String description;

    // actual uploaded file
    private MultipartFile file;
}