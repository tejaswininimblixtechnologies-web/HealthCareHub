package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.response.ApiResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse> activatePatient(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.PATIENT) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("error", "This user is not a patient", LocalDateTime.now()));
        }

        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok(
                new ApiResponse("success", "Patient activated successfully", LocalDateTime.now())
        );
    }

    @Override
    public ResponseEntity<ApiResponse> deactivatePatient(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.PATIENT) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("error", "This user is not a patient", LocalDateTime.now()));
        }

        user.setEnabled(false);
        userRepository.save(user);

        return ResponseEntity.ok(
                new ApiResponse("success", "Patient deactivated successfully", LocalDateTime.now())
        );
    }
}