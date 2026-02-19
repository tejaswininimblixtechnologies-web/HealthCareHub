package nimblix.in.HealthCareHub.helper;

import lombok.extern.slf4j.Slf4j;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class UploadImageHelper {

    // ================= MULTIPLE FILE UPLOAD =================
    public MultipleImageResponse uploadImages(List<MultipartFile> pictures) throws Exception {

        List<String> uploadedFileNames = new ArrayList<>();
        List<String> failedFileNames = new ArrayList<>();

        if (pictures == null || pictures.isEmpty()) {
            return new MultipleImageResponse(
                    HealthCareConstants.STATUS_ERORR,
                    "No files provided",
                    Collections.emptyList());
        }

        for (MultipartFile file : pictures) {

            if (file == null || file.isEmpty()) {
                failedFileNames.add(file != null ? file.getOriginalFilename() : "Unknown file");
                continue;
            }

            try {

                // unique file name
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

                // project root path
                String projectPath = System.getProperty("user.dir");

                // create uploads folder inside project
                File directory = new File(projectPath + File.separator + "uploads");
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // final path
                Path path = Paths.get(directory.getAbsolutePath(), fileName);

                // save file
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                log.info("File saved successfully at: {}", path);

                // IMPORTANT → store relative path (used by download)
                uploadedFileNames.add("uploads/" + fileName);

            } catch (Exception e) {
                log.error("Error saving file: {}", file.getOriginalFilename(), e);
                failedFileNames.add(file.getOriginalFilename());
            }
        }

        if (uploadedFileNames.isEmpty()) {
            return new MultipleImageResponse(
                    HealthCareConstants.STATUS_ERORR,
                    "Image upload failed",
                    Collections.emptyList());
        }

        return new MultipleImageResponse(
                HealthCareConstants.STATUS_SUCCESS,
                "Image upload successful",
                uploadedFileNames);
    }

    // ================= SINGLE FILE UPLOAD (used by PatientService) =================
    public String uploadFile(MultipartFile file) {

        try {

            List<MultipartFile> fileList = new ArrayList<>();
            fileList.add(file);

            MultipleImageResponse response = uploadImages(fileList);

            if (response.getUploadedFileNames() != null &&
                    !response.getUploadedFileNames().isEmpty()) {

                return response.getUploadedFileNames().get(0);
            }

            throw new RuntimeException("File upload failed");

        } catch (Exception e) {
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }
    }
}
