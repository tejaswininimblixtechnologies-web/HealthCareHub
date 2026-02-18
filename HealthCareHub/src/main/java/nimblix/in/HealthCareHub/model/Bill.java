package nimblix.in.HealthCareHub.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private Double amount;

    private String description;

    private LocalDate billDate;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getBillDate() { return billDate; }
    public void setBillDate(LocalDate billDate) { this.billDate = billDate; }
}