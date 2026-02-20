package nimblix.in.HealthCareHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="medicines")
public class Medicine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicineName;
    private String manufacturer;
    private Double price;
    private Integer stock;
    private String expiryDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
