package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Room;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;

public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);

    // ADD THIS
    Room addRoom(Long hospitalId, Room room);
}