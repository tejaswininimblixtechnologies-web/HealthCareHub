package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.model.Bill;
import nimblix.in.HealthCareHub.repository.BillRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl {

    private final BillRepository billRepository;

    // Create new empty bill
    public Bill createBill() {
        Bill bill = new Bill();
        bill.setTotalAmount(0.0);
        return billRepository.save(bill);
    }

    // Get all bills
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    // Get bill by id
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
    }

    // Delete bill
    public void deleteBill(Long id) {
        Bill bill = getBillById(id);
        billRepository.delete(bill);
    }
}