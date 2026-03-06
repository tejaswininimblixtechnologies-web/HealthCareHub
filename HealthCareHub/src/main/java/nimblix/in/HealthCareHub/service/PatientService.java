package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.MultipleImageResponse;
import nimblix.in.HealthCareHub.request.ForgotPasswordRequest;
import nimblix.in.HealthCareHub.request.ResetPasswordRequest;


public interface PatientService {
    MultipleImageResponse forgotPassword(ForgotPasswordRequest request);
    MultipleImageResponse resetPassword(ResetPasswordRequest request);
}
