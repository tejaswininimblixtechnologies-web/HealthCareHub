package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // ✅ Task 1 – Registration
    @Override
    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // ✅ Task 4 – Update Profile
    @Override
    public Patient updatePatient(Long id, Patient newPatient) {

        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        existing.setName(newPatient.getName());
        existing.setAge(newPatient.getAge());
        existing.setGender(newPatient.getGender());
        existing.setPhone(newPatient.getPhone());
        existing.setDisease(newPatient.getDisease());

        return patientRepository.save(existing);
    }

    // Get Patient
    @Override
    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // ✅ Task 2 – Download Medical Report
    @Override
    public ResponseEntity<Resource> downloadMedicalReport(Long id) {

        try {

            // 👉 Make sure this file exists in your system
            Path filePath = Paths.get("C:/reports/sample_medical_report.pdf");

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new RuntimeException("File not found at path: " + filePath);
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"medical_report.pdf\"")
                    .body(resource);

        } catch (Exception e) {
            throw new RuntimeException("Error downloading file: " + e.getMessage());
        }
    }

    // ✅ Task 3 – Upcoming Appointments
    @Override
    public List<String> getAppointments(Long id) {

        return Arrays.asList(
                "Cardiology Appointment - Tomorrow 10 AM",
                "Dental Appointment - Friday 2 PM"
        );
    }
}
