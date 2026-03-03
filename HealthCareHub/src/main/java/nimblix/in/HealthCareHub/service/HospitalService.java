package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;

import nimblix.in.HealthCareHub.response.HospitalResponse;
import nimblix.in.HealthCareHub.response.RoomResponse;

import java.util.List;

public interface HospitalService {

    HospitalResponse registerHospital(HospitalRegistrationRequest request);

    void addRooms(Long hospitalId, List<HospitalRegistrationRequest.Room> rooms);

    List<RoomResponse> getAvailableRooms(Long hospitalId);

}

