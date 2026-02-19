package nimblix.in.HealthCareHub.helper;

import lombok.extern.slf4j.Slf4j;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class UploadImageHelper {

    @Value("${assignment.upload.path}")
    private String uploadPath;

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".pdf", ".doc", ".docx");

    public MultipleImageResponse uploadImages(List<MultipartFile> pictures) throws Exception {
        List<String> uploadedFileNames = new ArrayList<>();
        List<String> failedFileNames = new ArrayList<>();

        if (pictures == null || pictures.isEmpty()) {
            return new MultipleImageResponse(HealthCareConstants.STATUS_ERORR, "No files provided", Collections.emptyList());
        }

        // Ensure upload directory exists (once, outside loop)
        String normalizedPath = uploadPath.endsWith(File.separator) ? uploadPath : uploadPath + File.separator;
        File directory = new File(normalizedPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                log.info("Directory created at: {}", normalizedPath);
            } else {
                log.error("Failed to create directory at: {}", normalizedPath);
                return new MultipleImageResponse(HealthCareConstants.STATUS_ERORR, "Failed to create upload directory", Collections.emptyList());
            }
        }

        for (MultipartFile file : pictures) {
            if (file == null || file.isEmpty()) {
                failedFileNames.add(file != null ? file.getOriginalFilename() : "Unknown file");
                continue;
            }

            String originalFileName = file.getOriginalFilename();
            
            // Validate file size
            if (file.getSize() > MAX_FILE_SIZE) {
                log.warn("File too large: {}", originalFileName);
                failedFileNames.add(originalFileName + " (too large)");
                continue;
            }

            // Validate file extension
            String extension = getFileExtension(originalFileName);
            if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
                log.warn("Invalid file type: {}", originalFileName);
                failedFileNames.add(originalFileName + " (invalid type)");
                continue;
            }

            // Sanitize and create unique filename
            String sanitizedName = originalFileName.replaceAll("[^a-zA-Z0-9.\\-]", "_");
            String fileName = System.currentTimeMillis() + "_" + sanitizedName;

            Path path = Paths.get(normalizedPath + fileName);
            byte[] fileData = file.getBytes();

            try {
                Files.write(path, fileData);
                log.info("File saved successfully: {}", fileName);
                uploadedFileNames.add(fileName);
            } catch (Exception e) {
                log.error("Error saving file: {}, error: {}", fileName, e.getMessage(), e);
                failedFileNames.add(originalFileName);
            }
        }

        if (uploadedFileNames.isEmpty()) {
            String failedFilesMessage = "Image upload failed for the following files: " + String.join(", ", failedFileNames);
            return new MultipleImageResponse(HealthCareConstants.STATUS_ERORR, failedFilesMessage, Collections.emptyList());
        }

        return new MultipleImageResponse(HealthCareConstants.STATUS_SUCCESS, "Image upload successful", uploadedFileNames);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
