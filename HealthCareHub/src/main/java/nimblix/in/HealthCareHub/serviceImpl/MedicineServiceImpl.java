package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.response.MedicineResponse;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public List<MedicineResponse> getExpiringMedicines(int days) {

        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(days);

        List<Medicine> medicines =
                medicineRepository.findByExpiryDateBetween(today, futureDate);

        return medicines.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicineResponse> getLowStockMedicines(int limit) {

        List<Medicine> medicines =
                medicineRepository.findByStockQuantityLessThanEqual(limit);

        return medicines.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private MedicineResponse mapToResponse(Medicine medicine) {

        return MedicineResponse.builder()
                .id(medicine.getId())
                .name(medicine.getName())
                .stockQuantity(medicine.getStockQuantity())
                .manufacturer(medicine.getManufacturer())
                .expiryDate(medicine.getExpiryDate())
                .build();
    }
}