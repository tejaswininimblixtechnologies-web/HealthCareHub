package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;

import java.util.List;

public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);

    List<Hospital.RoomInfo> getAvailableRooms(Long hospitalId);
}