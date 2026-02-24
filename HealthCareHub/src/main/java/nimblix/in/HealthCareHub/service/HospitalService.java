package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.HospitalRequestDTO;
import nimblix.in.HealthCareHub.dto.HospitalResponseDTO;

import java.util.List;

public interface HospitalService {

    HospitalResponseDTO createHospital(HospitalRequestDTO request);

    List<HospitalResponseDTO> getAllHospitals();
}