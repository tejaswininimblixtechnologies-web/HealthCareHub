package nimblix.in.HealthCareHub.utility;

import nimblix.in.HealthCareHub.response.MedicineResponse;
import nimblix.in.HealthCareHub.response.PaginatedMedicineResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class PaginationUtil {

    public static PaginatedMedicineResponse buildMedicineResponse(
            Page<?> page,
            List<MedicineResponse> list
    ) {

        return PaginatedMedicineResponse.builder()
                .status("SUCCESS")
                .message("Medicines fetched successfully")
                .medicines(list)
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
    }
}
