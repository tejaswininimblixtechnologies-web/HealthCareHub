package nimblix.in.HealthCareHub.service;

import java.util.List;

import nimblix.in.HealthCareHub.dto.HospitalDropdownDTO;
import nimblix.in.HealthCareHub.model.Hospital;

public interface HospitalService {

    String saveHospital(Hospital hospital);

    List<HospitalDropdownDTO> getHospitalList();
}