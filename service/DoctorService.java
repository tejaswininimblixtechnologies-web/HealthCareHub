
package nimblix.in.HealthCareHub.service;


import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.response.DoctorListResponse;
import nimblix.in.HealthCareHub.response.DoctorProfileResponse;


import java.util.List;

public interface DoctorService {

    List<String> getAllRoles();

    List<DoctorProfileResponse> filterDoctorsBySpecialization(String specialization);

    DoctorListResponse getDoctorsByHospital(Long hospitalId);

    DoctorProfileResponse addDoctor(DoctorRegistrationRequest request);



}
