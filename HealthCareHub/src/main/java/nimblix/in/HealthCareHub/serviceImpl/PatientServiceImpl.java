package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.exception.UserNotFoundException;
import nimblix.in.HealthCareHub.helper.UploadImageHelper;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.model.PatientDocument;
import nimblix.in.HealthCareHub.repository.PatientDocumentRepository;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.PatientDocumentUploadRequest;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import nimblix.in.HealthCareHub.response.PatientDocumentUploadResponse;
import nimblix.in.HealthCareHub.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientDocumentRepository patientDocumentRepository;

    @Autowired
    private UploadImageHelper uploadImageHelper;

    @Autowired
    private UserRepository userRepository;


    @Override
    public PatientDocumentUploadResponse uploadPatientDocument(PatientDocumentUploadRequest request) {

        // Validate patient (user) exists
        User patient = userRepository.findById(request.getPatientId())
                .orElseThrow(() ->
                        new UserNotFoundException("Patient not found with ID: " + request.getPatientId()));

        // 2. Validate File
        MultipartFile file = request.getFile();

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Uploaded file is empty.");
        }

        // file type validation
        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                !(fileName.endsWith(".pdf") || fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"))) {
            throw new RuntimeException("Only PDF, JPG, JPEG, PNG files are allowed.");
        }

        // convert single file to list (because helper accepts list)
        List<MultipartFile> files = new ArrayList<>();
        files.add(file);

        MultipleImageResponse uploadResponse;

        try {
            uploadResponse = uploadImageHelper.uploadImages(files);
        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }

// check upload success
        if (!"SUCCESS".equalsIgnoreCase(uploadResponse.getStatus())) {
            throw new RuntimeException("File upload failed");
        }

// helper returns file names list
        String savedFileName = uploadResponse.getUploadedFileNames().get(0);

        //4. Save Metadata
        PatientDocument document = new PatientDocument();
        document.setPatient(patient);
        document.setFileName(savedFileName);
        document.setFilePath(savedFileName);
        document.setDocumentType(request.getDocumentType());
        document.setDescription(request.getDescription());
        document.setUploadedAt(LocalDateTime.now());

        PatientDocument savedDocument = patientDocumentRepository.save(document);

        //5. Prepare Response
        PatientDocumentUploadResponse response = new PatientDocumentUploadResponse();
        response.setDocumentId(savedDocument.getId());
        response.setFileName(savedDocument.getFileName());
        response.setDocumentType(savedDocument.getDocumentType());
        response.setUploadedAt(savedDocument.getUploadedAt());

        response.setMessage("Patient document uploaded successfully");

        return response;
    }
}
