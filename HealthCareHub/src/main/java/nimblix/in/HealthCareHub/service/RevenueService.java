package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.RevenueSummaryDTO;
import nimblix.in.HealthCareHub.entity.Revenue;
import nimblix.in.HealthCareHub.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    public RevenueSummaryDTO getRevenueSummary() {

        List<Revenue> list = revenueRepository.findAll();

        double total = list.stream()
                .mapToDouble(Revenue::getAmount)
                .sum();

        RevenueSummaryDTO dto = new RevenueSummaryDTO();
        dto.setTotalRevenue(total);
        dto.setTotalDepartments(list.size());

        return dto;
    }
}
