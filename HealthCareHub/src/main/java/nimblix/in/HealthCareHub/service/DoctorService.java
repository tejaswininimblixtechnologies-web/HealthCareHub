package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorResponse;

public interface DoctorService {

    DoctorResponse registerDoctor(DoctorRegistrationRequest request);

    DoctorResponse getDoctorDetails(Long doctorId, Long hospitalId);
}