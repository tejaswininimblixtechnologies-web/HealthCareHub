package nimblix.in.HealthCareHub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.repository.BillItemRepository;
import nimblix.in.HealthCareHub.repository.BillRepository;
import nimblix.in.HealthCareHub.model.Bill;
import nimblix.in.HealthCareHub.model.BillItem;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillItemService {

    private final BillItemRepository billItemRepository;
    private final BillRepository billRepository;

    public BillItem addItemToBill(Long billId, BillItem item) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        item.setBill(bill);
        item.setTotalAmount(item.getQuantity() * item.getUnitPrice());

        BillItem savedItem = billItemRepository.save(item);

        // Update total bill amount
        bill.setTotalAmount(
                bill.getTotalAmount() + savedItem.getTotalAmount()
        );
        billRepository.save(bill);

        return savedItem;
    }

    public List<BillItem> getItemsByBill(Long billId) {
        return billItemRepository.findByBillId(billId);
    }
}