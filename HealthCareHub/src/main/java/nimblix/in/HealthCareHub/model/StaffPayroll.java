package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff_payroll")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffPayroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long staffId;

    private Double baseSalary;

    private Double overtimeHours;

    private Double overtimeAmount;

    private Double bonus;

    private Double deductions;

    private Double netSalary;

    private String month;
}