package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.service.FinanceService;
import nimblix.in.HealthCareHub.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Double getRevenueSummary(LocalDate startDate, LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        if (endDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Future dates not allowed");
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        Double total = paymentRepository.getRevenueBetweenDates(startDateTime, endDateTime);

        return total != null ? total : 0.0;
    }
}
