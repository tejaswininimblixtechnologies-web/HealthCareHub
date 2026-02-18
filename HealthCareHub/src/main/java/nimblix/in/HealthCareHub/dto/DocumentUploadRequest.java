package nimblix.in.HealthCareHub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DocumentUploadRequest {

    @NotNull(message = "File is required")
    private MultipartFile file;
}

