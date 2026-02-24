package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.LoginRequest;
import nimblix.in.HealthCareHub.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}