package nimblix.in.HealthCareHub.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import nimblix.in.HealthCareHub.service.FileUploadCommonService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FileUploadCommonServiceImpl implements FileUploadCommonService {

    @Override
    public List<String> uploadFiles(List<MultipartFile> files, String uploadPath) throws Exception {
        List<String> uploadedFileNames = new ArrayList<>();

        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("No files provided");
        }

        ensureDirectoryExists(uploadPath);

        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                String fileName = uploadSingleFile(file, uploadPath);
                uploadedFileNames.add(fileName);
            }
        }

        return uploadedFileNames;
    }

    @Override
    public String uploadSingleFile(MultipartFile file, String uploadPath) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        ensureDirectoryExists(uploadPath);

        String normalizedPath = uploadPath.endsWith(File.separator) ? uploadPath : uploadPath + File.separator;
        Path path = Paths.get(normalizedPath + fileName);

        Files.write(path, file.getBytes());
        log.info("File saved successfully: {}", fileName);

        return fileName;
    }

    private void ensureDirectoryExists(String uploadPath) {
        File directory = new File(uploadPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                log.info("Directory created at: {}", uploadPath);
            } else {
                log.error("Failed to create directory at: {}", uploadPath);
            }
        }
    }
}
