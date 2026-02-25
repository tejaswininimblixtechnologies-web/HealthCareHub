package nimblix.in.HealthCareHub.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientDocumentUploadResponse {

    // DB generated document id
    private Long documentId;

    // file name uploaded by user
    private String fileName;

    // type of document
    private String documentType;

    // upload timestamp
    private LocalDateTime uploadedAt;

    // success message
    private String message;
}