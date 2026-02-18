package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicineName;

    private Integer quantity;

    private LocalDate orderDate;

    private String status; // PENDING / APPROVED / REJECTED

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
}