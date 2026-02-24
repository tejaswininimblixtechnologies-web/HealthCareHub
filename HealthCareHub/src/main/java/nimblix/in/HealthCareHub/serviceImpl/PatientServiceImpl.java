package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.FeedbackDto;
import nimblix.in.HealthCareHub.dto.PatientDto;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Feedback;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.repository.FeedbackRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final FeedbackRepository feedbackRepository;

    @Override
    public String registerPatient(PatientDto dto) {

        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setPhone(dto.getPhone());
        patient.setDisease(dto.getDisease());

        patientRepository.save(patient);

        return "Patient Registered Successfully";
    }

    @Override
    public ResponseEntity<Resource> downloadMedicalReport(Long patientId) {

        // Change file name here
        Path path = Paths.get("reports/sample_medical_report.pdf");

        Resource resource = new FileSystemResource(path);

        if (!resource.exists()) {
            throw new RuntimeException("Medical report not found!");
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=sample_medical_report.pdf")
                .body(resource);
    }
    @Override
    public List<Appointment> getUpcomingAppointments(Long patientId) {

        return appointmentRepository
                .findByPatientIdAndAppointmentDateAfter(
                        patientId,
                        LocalDateTime.now());
    }

    @Override
    public String updateProfile(Long patientId, PatientDto dto) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setPhone(dto.getPhone());
        patient.setDisease(dto.getDisease());

        patientRepository.save(patient);

        return "Profile Updated Successfully";
    }

    @Override
    public String submitFeedback(FeedbackDto dto) {

        Feedback feedback = new Feedback();
        feedback.setPatientId(dto.getPatientId());
        feedback.setDoctorId(dto.getDoctorId());
        feedback.setRating(dto.getRating());
        feedback.setComments(dto.getComments());

        feedbackRepository.save(feedback);

        return "Feedback Submitted Successfully";
    }
}