package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicineName;
    private String manufacturer;
    private Double price;
    private Integer stock;
    private String expiryDate;

    //--Mapping Medicine to Hospital--
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;



}
