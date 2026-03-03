package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.MedicineRequest;
import nimblix.in.HealthCareHub.response.MedicineResponse;

import java.util.List;

public interface MedicineService {

    String addMedicine(Long hospitalId, MedicineRequest request);

    List<MedicineResponse> getHospitalMedicines(Long hospitalId);

    List<MedicineResponse> getLowStockMedicines(int limit);

}