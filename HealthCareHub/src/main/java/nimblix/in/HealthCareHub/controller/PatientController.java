package nimblix.in.HealthCareHub.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nimblix.in.HealthCareHub.request.ForgotPasswordRequest;
import nimblix.in.HealthCareHub.request.ResetPasswordRequest;
import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @Operation(
            summary = "Forgot Password",
            description = "This API verifies the patient using the registered phone number.Tf the phone number exists in the system,the user will be allowed to reset password. "
    )
    @PostMapping("/forgot-password")
    public ResponseEntity<MultipleImageResponse> forgotPassword(@RequestBody ForgotPasswordRequest request) {

        return ResponseEntity.ok(patientService.forgotPassword(request));
    }

    @Operation(
            summary = "Reset Password",
            description = "This API allows the patient to reset  using the registered phone number and new password."
    )

    @PostMapping("/reset-password")
    public ResponseEntity<MultipleImageResponse> resetPassword(@RequestBody ResetPasswordRequest request) {

        return ResponseEntity.ok(patientService.resetPassword(request));
    }
}
