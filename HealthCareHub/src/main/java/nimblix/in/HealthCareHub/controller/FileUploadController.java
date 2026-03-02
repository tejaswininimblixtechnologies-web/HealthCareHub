package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.service.FileUploadCommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadCommonService fileUploadService;

    @Value("${assignment.upload.path}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        try {
            List<String> uploadedFiles = fileUploadService.uploadFiles(files, uploadPath);
            return ResponseEntity.ok(uploadedFiles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(List.of("Upload failed: " + e.getMessage()));
        }
    }

    @PostMapping("/upload-single")
    public ResponseEntity<String> uploadSingleFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileUploadService.uploadSingleFile(file, uploadPath);
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }
}
