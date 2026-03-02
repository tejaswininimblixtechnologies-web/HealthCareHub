package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.UpdateRoomStatusRequest;
import nimblix.in.HealthCareHub.response.UpdateRoomStatusResponse;

import java.util.List;

public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);

    UpdateRoomStatusResponse updateRoomStatus(Long hospitalId, UpdateRoomStatusRequest request);;
}
