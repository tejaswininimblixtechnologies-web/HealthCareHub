package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private Integer quantity;
    private Double unitPrice;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
}
