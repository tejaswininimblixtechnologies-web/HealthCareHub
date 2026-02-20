package nimblix.in.HealthCareHub.response;

import java.time.LocalDate;

public class BillResponse {

    private Double amount;
    private String description;
    private LocalDate billDate;

    public BillResponse(Double amount, String description, LocalDate billDate) {
        this.amount = amount;
        this.description = description;
        this.billDate = billDate;
    }

    public Double getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDate getBillDate() { return billDate; }
}