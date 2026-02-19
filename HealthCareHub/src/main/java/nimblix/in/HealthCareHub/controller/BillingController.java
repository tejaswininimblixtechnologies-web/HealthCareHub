package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Bill;
import nimblix.in.HealthCareHub.model.BillItem;
import nimblix.in.HealthCareHub.repository.BillRepository;
import nimblix.in.HealthCareHub.repository.BillItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillItemRepository billItemRepository;

    @PostMapping("/add-item/{billId}")
    public ResponseEntity<?> addBillItem(@PathVariable Long billId,
                                         @RequestBody BillItem billItem) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        double total = billItem.getQuantity() * billItem.getUnitPrice();
        billItem.setTotalAmount(total);

        billItem.setBill(bill);
        billItemRepository.save(billItem);

        return ResponseEntity.ok("Bill item added successfully");
    }
}
