package nimblix.in.HealthCareHub.response;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedMedicineResponse {

    private String status;
    private String message;

    private List<MedicineResponse> medicines;

    private int currentPage;
    private int totalPages;
    private long totalElements;
}
