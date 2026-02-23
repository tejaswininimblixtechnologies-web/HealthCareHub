package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.LocationResponse;

import java.util.List;

public interface HospitalService {
    List<LocationResponse> getAllLocations();
}
