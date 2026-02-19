package nimblix.in.HealthCareHub.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nimblix.in.HealthCareHub.model.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedUserResponse {

    private List<User> users;

    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;
}
