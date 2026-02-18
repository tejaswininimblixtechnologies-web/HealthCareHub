package nimblix.in.HealthCareHub.controller;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.PatientDocument;
import nimblix.in.HealthCareHub.service.DocumentService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
@RestController
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/{id}")
    public ResponseEntity<FileSystemResource> downloadDocument(@PathVariable Long id) {

        File file = documentService.getDocumentFile(id);
        PatientDocument document = documentService.getDocument(id);

        FileSystemResource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + document.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(document.getFileType()))
                .body(resource);
    }
}
