package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.request.MedicineRequest;
import nimblix.in.HealthCareHub.response.MedicineResponse;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public String addMedicine(Long hospitalId, MedicineRequest request) {

        // Edge Case 1
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        // Edge Case 2
        if (request.getMedicineName() == null || request.getMedicineName().isBlank()) {
            throw new RuntimeException("Medicine name is required");
        }

        // Edge Case 3
        if (request.getPrice() == null || request.getPrice() <= 0) {
            throw new RuntimeException("Invalid price");
        }

        Medicine medicine = Medicine.builder()
                .medicineName(request.getMedicineName())
                .manufacturer(request.getManufacturer())
                .description(request.getDescription())
                .dosage(request.getDosage())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .isActive("ACTIVE")
                .hospital(hospital)
                .build();

        medicineRepository.save(medicine);

        return "Medicine added successfully";
    }

    @Override
    public List<MedicineResponse> getHospitalMedicines(Long hospitalId) {

        if (!hospitalRepository.existsById(hospitalId)) {
            throw new RuntimeException("Hospital not found");
        }

        return medicineRepository.findMedicinesByHospital(hospitalId);
    }

    @Override
    public List<MedicineResponse> getLowStockMedicines(int limit) {

        return medicineRepository.findLowStockMedicines(limit);
    }
}