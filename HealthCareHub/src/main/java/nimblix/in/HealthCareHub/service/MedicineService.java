package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Medicine;

import java.util.List;

public interface MedicineService {

    List<Medicine> getExpiringMedicines(int days);

    List<Medicine> getLowStockMedicines(int limit);

}
