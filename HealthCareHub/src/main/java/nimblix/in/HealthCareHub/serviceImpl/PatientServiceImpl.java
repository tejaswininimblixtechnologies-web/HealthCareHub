package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.response.FeedbackDto;
import nimblix.in.HealthCareHub.response.PatientDto;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    // 🔹 Task 56 — Patient Registration
    @Override
    public PatientDto registerPatient(PatientDto dto) {

        Patient patient = Patient.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .gender(dto.getGender())
                .phone(dto.getPhone())
                .disease(dto.getDisease())
                .build();

        Patient savedPatient = patientRepository.save(patient);

        return mapToDto(savedPatient);
    }

    // 🔹 Task 57 — Download Medical Report
    @Override
    public ResponseEntity<Resource> downloadMedicalReport(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // File path (make sure file exists here)
        String filePath = "C:/reports/sample_medical_report.pdf";

        try {

            Path path = Paths.get(filePath);
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()) {
                throw new RuntimeException("File not found");
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                    .contentLength(path.toFile().length())
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException("Error while downloading file");
        }
    }

    // 🔹 Task 58 — Upcoming Appointments
    @Override
    public List<String> getUpcomingAppointments(Long patientId) {

        patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return Arrays.asList(
                "Cardiology Appointment - Tomorrow 10 AM",
                "Dental Appointment - Friday 2 PM"
        );
    }

    // 🔹 Task 59 — Update Profile
    @Override
    public PatientDto updateProfile(Long patientId, PatientDto dto) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setPhone(dto.getPhone());
        patient.setDisease(dto.getDisease());

        Patient updatedPatient = patientRepository.save(patient);

        return mapToDto(updatedPatient);
    }

    // 🔹 Task 60 — Feedback & Rating
    @Override
    public String submitFeedback(FeedbackDto dto) {

        Patient patient = patientRepository
                .findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // If Patient has rating/feedback fields you can store here

        return "Feedback submitted successfully";
    }


    // ✅ Mapping Method (Entity → DTO)
    private PatientDto mapToDto(Patient patient) {

        PatientDto dto = new PatientDto();

        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setAge(patient.getAge());
        dto.setGender(patient.getGender());
        dto.setPhone(patient.getPhone());
        dto.setDisease(patient.getDisease());

        return dto;
    }
}