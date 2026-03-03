package nimblix.in.HealthCareHub.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationHelper {

    private PaginationHelper() {
        // Prevent instantiation
    }

    public static Pageable createPageable(
            int page,
            int size,
            String sortBy,
            String direction) {

        // Edge case handling
        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        if (sortBy == null || sortBy.isBlank()) sortBy = "id";
        if (direction == null || direction.isBlank()) direction = "asc";

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return PageRequest.of(page, size, sort);
    }
}