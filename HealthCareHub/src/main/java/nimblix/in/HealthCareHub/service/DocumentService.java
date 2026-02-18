package nimblix.in.HealthCareHub.service;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.PatientDocument;
import nimblix.in.HealthCareHub.repository.PatientDocumentRepository;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final PatientDocumentRepository documentRepository;

    public File getDocumentFile(Long documentId) {

        PatientDocument document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        // REAL ABSOLUTE PATH
        String fullPath = System.getProperty("user.dir")
                + File.separator
                + document.getFilePath();

        File file = new File(fullPath);

        if (!file.exists()) {
            throw new RuntimeException("File not found at: " + fullPath);
        }

        return file;
    }

    public PatientDocument getDocument(Long documentId){
        return documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }
}
