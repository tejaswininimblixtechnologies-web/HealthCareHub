package nimblix.in.HealthCareHub.service;
import java.time.LocalDate;

public interface FinanceService {
    Double getRevenueSummary(LocalDate startDate, LocalDate endDate);
}
