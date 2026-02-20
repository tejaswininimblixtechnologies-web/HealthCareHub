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
import java.nio.file.StandardOpenOption;
import java.util.*;

@Slf4j
@Component
public class UploadImageHelper {

    @Value("${assignment.upload.path}")
    private String uploadPath;

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final List<String> ALLOWED_EXTENSIONS =
            Arrays.asList(".jpg", ".jpeg", ".png", ".pdf", ".doc", ".docx");

    public MultipleImageResponse uploadImages(List<MultipartFile> pictures) {

        List<String> uploadedFileNames = new ArrayList<>();
        List<String> failedFileNames = new ArrayList<>();

        if (pictures == null || pictures.isEmpty()) {
            return new MultipleImageResponse(
                    HealthCareConstants.STATUS_ERROR,
                    "No files provided",
                    Collections.emptyList());
        }

        if (uploadPath == null || uploadPath.trim().isEmpty()) {
            return new MultipleImageResponse(
                    HealthCareConstants.STATUS_ERROR,
                    "Upload path not configured",
                    Collections.emptyList());
        }

        try {
            String normalizedPath = uploadPath.endsWith(File.separator)
                    ? uploadPath
                    : uploadPath + File.separator;

            File directory = new File(normalizedPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            for (MultipartFile file : pictures) {

                if (file == null || file.isEmpty()) {
                    failedFileNames.add("Unknown file");
                    continue;
                }

                String originalFileName = file.getOriginalFilename();
                if (originalFileName == null) {
                    failedFileNames.add("Unnamed file");
                    continue;
                }

                // Validate file size
                if (file.getSize() > MAX_FILE_SIZE) {
                    failedFileNames.add(originalFileName + " (too large)");
                    continue;
                }

                // Validate extension
                String extension = getFileExtension(originalFileName);
                if (!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())) {
                    failedFileNames.add(originalFileName + " (invalid type)");
                    continue;
                }

                // Sanitize filename
                String sanitizedName =
                        originalFileName.replaceAll("[^a-zA-Z0-9.\\-]", "_");

                String fileName =
                        System.currentTimeMillis() + "_" + sanitizedName;

                Path path = Paths.get(normalizedPath, fileName);

                Files.write(
                        path,
                        file.getBytes(),
                        StandardOpenOption.CREATE);

                uploadedFileNames.add(fileName);
            }

        } catch (Exception e) {
            log.error("File upload error: {}", e.getMessage(), e);
            return new MultipleImageResponse(
                    HealthCareConstants.STATUS_ERROR,
                    "File upload failed due to server error",
                    Collections.emptyList());
        }

        if (uploadedFileNames.isEmpty()) {
            return new MultipleImageResponse(
                    HealthCareConstants.STATUS_ERROR,
                    "Image upload failed for: " + String.join(", ", failedFileNames),
                    Collections.emptyList());
        }

        return new MultipleImageResponse(
                HealthCareConstants.STATUS_SUCCESS,
                "Image upload successful",
                uploadedFileNames);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
