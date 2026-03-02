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

        return userRepository.findById(userId)
                .map(user -> {

                    if (user.getRole() != Role.PATIENT) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse("error",
                                        "This user is not a patient",
                                        LocalDateTime.now()));
                    }

                    if (user.isEnabled()) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse("error",
                                        "Patient already activated",
                                        LocalDateTime.now()));
                    }

                    user.setEnabled(true);
                    userRepository.save(user);

                    return ResponseEntity.ok(
                            new ApiResponse("success",
                                    "Patient activated successfully",
                                    LocalDateTime.now()));
                })
                .orElseGet(() ->
                        ResponseEntity.status(404)
                                .body(new ApiResponse("error",
                                        "User not found",
                                        LocalDateTime.now())));
    }

    @Override
    public ResponseEntity<ApiResponse> deactivatePatient(Long userId) {

        return userRepository.findById(userId)
                .map(user -> {

                    if (user.getRole() != Role.PATIENT) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse("error",
                                        "This user is not a patient",
                                        LocalDateTime.now()));
                    }

                    if (!user.isEnabled()) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse("error",
                                        "Patient already deactivated",
                                        LocalDateTime.now()));
                    }

                    user.setEnabled(false);
                    userRepository.save(user);

                    return ResponseEntity.ok(
                            new ApiResponse("success",
                                    "Patient deactivated successfully",
                                    LocalDateTime.now()));
                })
                .orElseGet(() ->
                        ResponseEntity.status(404)
                                .body(new ApiResponse("error",
                                        "User not found",
                                        LocalDateTime.now())));
    }
}