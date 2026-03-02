package nimblix.in.HealthCareHub.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PatientDocumentUploadRequest {

    private Long patientId;

    private String documentType;

    private MultipartFile file;
}