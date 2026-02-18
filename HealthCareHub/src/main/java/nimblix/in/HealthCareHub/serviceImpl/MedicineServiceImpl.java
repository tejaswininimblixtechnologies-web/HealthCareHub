package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }
}
