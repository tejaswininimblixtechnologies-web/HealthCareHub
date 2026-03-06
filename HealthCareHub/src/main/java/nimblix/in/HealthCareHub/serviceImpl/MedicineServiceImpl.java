
package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.helper.PaginationHelper;
import nimblix.in.HealthCareHub.model.Medicine;

import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.response.MedicineResponse;
import nimblix.in.HealthCareHub.response.PaginatedMedicineResponse;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public PaginatedMedicineResponse getAllMedicines(int page, int size) {

        Page<MedicineResponse> medicinePage =
                medicineRepository.findAllMedicineResponses(
                        PaginationHelper.getPageable(page, size));

        return PaginatedMedicineResponse.builder()
                .status("SUCCESS")
                .message("Medicines fetched successfully")
                .medicines(medicinePage.getContent())
                .currentPage(medicinePage.getNumber())
                .totalPages(medicinePage.getTotalPages())
                .totalElements(medicinePage.getTotalElements())
                .build();
    }
}
