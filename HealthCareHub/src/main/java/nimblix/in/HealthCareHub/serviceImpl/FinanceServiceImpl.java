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
    public Double getRevenueSummary(String startDate, String endDate) {

        LocalDateTime startDateTime = LocalDate.parse(startDate).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate).atTime(23, 59, 59);

        Double total = paymentRepository.getRevenueBetweenDates(
                startDateTime.toString(),
                endDateTime.toString()
        );

        return total != null ? total : 0.0;
    }
}