package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.response.MedicineResponse;
import nimblix.in.HealthCareHub.response.PaginatedMedicineResponse;
import nimblix.in.HealthCareHub.service.MedicineService;
import nimblix.in.HealthCareHub.utility.PaginationUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository repository;

    @Override
    public PaginatedMedicineResponse getAllMedicines(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        // Fetch DTO directly from DB
        Page<MedicineResponse> medicinePage =
                repository.findAllMedicineResponses(pageable);

        //Use pagination helper
        return PaginationUtil.buildMedicineResponse(
                medicinePage,
                medicinePage.getContent()
        );
    }
}
