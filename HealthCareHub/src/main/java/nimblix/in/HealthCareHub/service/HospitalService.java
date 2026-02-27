package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.RoomRequest;

public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);

    void addRoom(Long hospitalId, RoomRequest request);
}