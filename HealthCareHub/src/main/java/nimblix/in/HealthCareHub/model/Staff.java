package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "staff")
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    private String designation;  // Nurse, LabTech, Receptionist
    private String department;

    private double baseSalary;
}