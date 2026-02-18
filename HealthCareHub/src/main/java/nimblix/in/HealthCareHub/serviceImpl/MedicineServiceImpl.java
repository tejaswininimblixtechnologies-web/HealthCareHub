package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public List<Medicine> getExpiringMedicines(int days) {

        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(days);

        return medicineRepository.findByExpiryDateBetween(today, futureDate);
    }

    @Override
    public List<Medicine> getLowStockMedicines(int limit) {
        return medicineRepository.findByStockQuantityLessThanEqual(limit);
    }
}