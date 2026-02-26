package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.MedicineAddRequest;

public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);
    String addMedicine(Long hospitalId, MedicineAddRequest request);
}
