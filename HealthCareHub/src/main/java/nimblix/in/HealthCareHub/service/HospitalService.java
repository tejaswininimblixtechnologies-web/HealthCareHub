package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.model.User;
import java.util.List;


public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);

    User addStaff(User user);

    List<User> getAllStaff();

}
