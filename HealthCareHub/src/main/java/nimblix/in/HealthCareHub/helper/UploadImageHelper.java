package nimblix.in.HealthCareHub.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UploadImageHelper {

    private final FileUploadService fileUploadService;
    
    @Value("${assignment.upload.path}")
    private String uploadPath;

    public MultipleImageResponse uploadImages(List<MultipartFile> pictures) throws Exception {
        if (pictures == null || pictures.isEmpty()) {
            return new MultipleImageResponse(HealthCareConstants.STATUS_ERORR, "No files provided", Collections.emptyList());
        }

        List<String> uploadedFileNames = new ArrayList<>();
        List<String> failedFileNames = new ArrayList<>();

        for (MultipartFile file : pictures) {
            if (file == null || file.isEmpty()) {
                failedFileNames.add(file != null ? file.getOriginalFilename() : "Unknown file");
                continue;
            }

            try {
                String fileName = fileUploadService.uploadFile(file, uploadPath);
                uploadedFileNames.add(fileName);
            } catch (Exception e) {
                log.error("Error saving file: {}", file.getOriginalFilename(), e);
                failedFileNames.add(file.getOriginalFilename());
            }
        }

        if (uploadedFileNames.isEmpty()) {
            return new MultipleImageResponse(HealthCareConstants.STATUS_ERORR, 
                "Image upload failed for: " + String.join(", ", failedFileNames), Collections.emptyList());
        }

        return new MultipleImageResponse(HealthCareConstants.STATUS_SUCCESS, "Image upload successful", uploadedFileNames);
    }

}
