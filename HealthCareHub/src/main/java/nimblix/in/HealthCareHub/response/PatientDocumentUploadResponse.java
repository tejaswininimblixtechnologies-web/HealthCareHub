package nimblix.in.HealthCareHub.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class PatientDocumentUploadResponse {

    // Generated document id
    private Long documentId;

    // Original file name
    private String fileName;

    // Type of document
    private String documentType;

    // Upload time
    private String uploadedAt;

    // Success message
    private String message;
}