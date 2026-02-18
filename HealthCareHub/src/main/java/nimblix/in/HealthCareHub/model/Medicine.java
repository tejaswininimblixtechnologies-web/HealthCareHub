package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="medicine")
public class Medicine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicineName;
    private String manufacture;
    private Double price;
    private Integer stock;
    private String expiryDate;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
}
