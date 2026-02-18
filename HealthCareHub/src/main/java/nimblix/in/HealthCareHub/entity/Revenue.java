package nimblix.in.HealthCareHub.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Revenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department;

    private double amount;

    private String month;
}
