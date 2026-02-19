package nimblix.in.HealthCareHub.helper;

import lombok.extern.slf4j.Slf4j;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UploadImageHelper {

    private final String UPLOAD_DIR = "uploads/";

    public MultipleImageResponse uploadMultipleImages(List<MultipartFile> files) {

        List<String> uploadedFileNames = new ArrayList<>();

        try {
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            for (MultipartFile file : files) {

                String fileName = file.getOriginalFilename();

                if (fileName == null || fileName.isEmpty()) {
                    continue;
                }

                File destinationFile =
                        new File(UPLOAD_DIR + File.separator + fileName);

                file.transferTo(destinationFile);

                uploadedFileNames.add(fileName);

                log.info("Uploaded: " + fileName);
            }

            return new MultipleImageResponse(
                    "200",
                    "Images uploaded successfully",
                    uploadedFileNames
            );

        } catch (Exception e) {

            log.error("Upload failed", e);

            return new MultipleImageResponse(
                    "500",
                    "Image upload failed",
                    new ArrayList<>()
            );
        }
    }
}



