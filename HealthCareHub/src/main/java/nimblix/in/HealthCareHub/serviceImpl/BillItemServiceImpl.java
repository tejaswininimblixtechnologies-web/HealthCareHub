package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.model.Bill;
import nimblix.in.HealthCareHub.model.BillItem;
import nimblix.in.HealthCareHub.repository.BillItemRepository;
import nimblix.in.HealthCareHub.repository.BillRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillItemServiceImpl {

    private final BillItemRepository billItemRepository;
    private final BillRepository billRepository;

    // Add item to bill
    public BillItem addItemToBill(Long billId, BillItem item) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + billId));

        // Set bill reference
        item.setBill(bill);

        // Calculate item total
        double itemTotal = item.getQuantity() * item.getUnitPrice();
        item.setTotalAmount(itemTotal);

        // Save item
        BillItem savedItem = billItemRepository.save(item);

        // Update bill total amount
        double updatedTotal = bill.getTotalAmount() + itemTotal;
        bill.setTotalAmount(updatedTotal);
        billRepository.save(bill);

        return savedItem;
    }

    // Get all items of a bill
    public List<BillItem> getItemsByBill(Long billId) {
        return billItemRepository.findByBillId(billId);
    }

    // Delete bill item
    public void deleteItem(Long itemId) {

        BillItem item = billItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Bill item not found with id: " + itemId));

        Bill bill = item.getBill();

        // Reduce total from bill
        double updatedTotal = bill.getTotalAmount() - item.getTotalAmount();
        bill.setTotalAmount(updatedTotal);
        billRepository.save(bill);

        billItemRepository.delete(item);
    }
}