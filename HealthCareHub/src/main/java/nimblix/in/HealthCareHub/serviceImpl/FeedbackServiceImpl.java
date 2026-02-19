package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.FeedbackRequest;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.Feedback;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.repository.FeedbackRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.service.FeedbackService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public String submitFeedback(FeedbackRequest request) {

        // ✅ Rating Validation
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        // ✅ Fetch Patient
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException("Patient not found with id: " + request.getPatientId()));

        // ✅ Fetch Doctor
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found with id: " + request.getDoctorId()));

        // ✅ Create Feedback Object
        Feedback feedback = Feedback.builder()
                .patient(patient)
                .doctor(doctor)
                .comments(request.getComments())
                .rating(request.getRating())
                .build();

        // ✅ Save to Database
        feedbackRepository.save(feedback);

        return "Feedback and Rating submitted successfully";
    }
}
