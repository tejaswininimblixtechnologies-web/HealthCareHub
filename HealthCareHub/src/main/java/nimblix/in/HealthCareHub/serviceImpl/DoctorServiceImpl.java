package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.model.User;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.ApiResponse;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final UserRepository userRepository;

    @Override
    public String registerDoctor(DoctorRegistrationRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> getDoctorDetails(Long doctorId, Long hospitalId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> activateDoctor(Long userId) {

        return userRepository.findById(userId)
                .map(user -> {

                    if (user.getRole() != Role.DOCTOR) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse("error",
                                        "This user is not a doctor",
                                        LocalDateTime.now()));
                    }

                    if (user.isEnabled()) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse("error",
                                        "Doctor already activated",
                                        LocalDateTime.now()));
                    }

                    user.setEnabled(true);
                    userRepository.save(user);

                    return ResponseEntity.ok(
                            new ApiResponse("success",
                                    "Doctor activated successfully",
                                    LocalDateTime.now()));
                })
                .orElseGet(() ->
                        ResponseEntity.status(404)
                                .body(new ApiResponse("error",
                                        "User not found",
                                        LocalDateTime.now())));
    }

    @Override
    public ResponseEntity<ApiResponse> deactivateDoctor(Long userId) {

        return userRepository.findById(userId)
                .map(user -> {

                    if (user.getRole() != Role.DOCTOR) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse("error",
                                        "This user is not a doctor",
                                        LocalDateTime.now()));
                    }

                    if (!user.isEnabled()) {
                        return ResponseEntity.badRequest()
                                .body(new ApiResponse("error",
                                        "Doctor already deactivated",
                                        LocalDateTime.now()));
                    }

                    user.setEnabled(false);
                    userRepository.save(user);

                    return ResponseEntity.ok(
                            new ApiResponse("success",
                                    "Doctor deactivated successfully",
                                    LocalDateTime.now()));
                })
                .orElseGet(() ->
                        ResponseEntity.status(404)
                                .body(new ApiResponse("error",
                                        "User not found",
                                        LocalDateTime.now())));
    }
}