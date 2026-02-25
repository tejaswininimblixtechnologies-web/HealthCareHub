package nimblix.in.HealthCareHub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.*;

@Service
@RequiredArgsConstructor
public class StaffPayrollService {

    private final StaffRepository staffRepository;
    private final StaffPayrollRepository payrollRepository;

    public StaffPayroll calculatePayroll(
            Long staffId,
            Double overtimeHours,
            Double bonus,
            Double deductions,
            String month) {

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Double overtimeRate = 200.0; // example rate
        Double overtimeAmount = overtimeHours * overtimeRate;

        Double netSalary = staff.getBaseSalary()
                + overtimeAmount
                + bonus
                - deductions;

        StaffPayroll payroll = StaffPayroll.builder()
                .staffId(staffId)
                .baseSalary(staff.getBaseSalary())
                .overtimeHours(overtimeHours)
                .overtimeAmount(overtimeAmount)
                .bonus(bonus)
                .deductions(deductions)
                .netSalary(netSalary)
                .month(month)
                .build();

        return payrollRepository.save(payroll);
    }
}