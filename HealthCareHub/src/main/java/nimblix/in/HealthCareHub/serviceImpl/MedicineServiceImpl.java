package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.repository.MedicineRepository;

import nimblix.in.HealthCareHub.response.MedicineResponse;
import nimblix.in.HealthCareHub.response.PaginatedMedicineResponse;

import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository repository;

    public MedicineServiceImpl(MedicineRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaginatedMedicineResponse getAllMedicines(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Medicine> medicinePage = repository.findAll(pageable);

        List<MedicineResponse> list =
                medicinePage.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        return PaginatedMedicineResponse.builder()
                .status("SUCCESS")
                .message("Medicines fetched successfully")
                .medicines(list)
                .currentPage(medicinePage.getNumber())
                .totalPages(medicinePage.getTotalPages())
                .totalElements(medicinePage.getTotalElements())
                .build();
    }

    private MedicineResponse mapToResponse(Medicine med) {

        return MedicineResponse.builder()
                .id(med.getId())
                .medicineName(med.getMedicineName())
                .manufacturer(med.getManufacturer())
                .description(med.getDescription())
                .dosage(med.getDosage())
                .price(med.getPrice())
                .stockQuantity(med.getStockQuantity())
                .isActive(med.getIsActive())
                .build();
    }
}
