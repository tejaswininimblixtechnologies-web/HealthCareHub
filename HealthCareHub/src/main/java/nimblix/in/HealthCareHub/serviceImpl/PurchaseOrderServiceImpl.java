package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.PurchaseOrderRequestDto;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.model.PurchaseOrder;
import nimblix.in.HealthCareHub.model.Vendor;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.repository.PurchaseOrderRepository;
import nimblix.in.HealthCareHub.repository.VendorRepository;
import nimblix.in.HealthCareHub.service.PurchaseOrderService;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final VendorRepository vendorRepository;
    private final MedicineRepository medicineRepository;


    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrderRequestDto dto) {

        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + dto.getVendorId()));

        PurchaseOrder order = new PurchaseOrder();
        order.setMedicineName(dto.getMedicineName());
        order.setQuantity(dto.getQuantity());
        order.setOrderDate(LocalDate.now());
        order.setStatus("PENDING");
        order.setVendor(vendor);

        return purchaseOrderRepository.save(order);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found with ID: " + id));
    }

    @Override
    public PurchaseOrder approvePurchaseOrder(Long id) {

        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        if (!order.getStatus().equals(HealthCareConstants.STATUS_PENDING)) {
            throw new RuntimeException("Only PENDING orders can be approved");
        }

        order.setStatus(HealthCareConstants.STATUS_APPROVED);

        return purchaseOrderRepository.save(order);
    }

    @Override
    public PurchaseOrder rejectPurchaseOrder(Long id) {

        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        if (!order.getStatus().equals(HealthCareConstants.STATUS_PENDING))
        {
            throw new RuntimeException("Only PENDING orders can be rejected");
        }

        order.setStatus(HealthCareConstants.STATUS_REJECTED);
        return purchaseOrderRepository.save(order);
    }

    @Override
    public PurchaseOrder receiveGoods(Long orderId) {

        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        if (!order.getStatus().equals("APPROVED")) {
            throw new RuntimeException("Only APPROVED orders can receive goods");
        }

        // Check if medicine already exists
        Medicine medicine = medicineRepository.findByName(order.getMedicineName())
                .orElseGet(() -> {
                    Medicine newMed = new Medicine();
                    newMed.setName(order.getMedicineName());
                    newMed.setStockQuantity(0);
                    return newMed;
                });

        // Update stock
        medicine.setStockQuantity(
                medicine.getStockQuantity() + order.getQuantity()
        );

        medicineRepository.save(medicine);

        // Update order status
        order.setStatus(HealthCareConstants.STATUS_COMPLETED);
        return purchaseOrderRepository.save(order);
    }

}
