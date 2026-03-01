package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Patient reference
    private Long patientId;

    // Bill amount
    private Double amount;

    // UPI / CARD / CASH
    private String paymentMode;

    // PAID / PENDING
    private String status;

    // Auto timestamp
    private LocalDateTime createdAt = LocalDateTime.now();
}