package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.model.PurchaseOrder;
import nimblix.in.HealthCareHub.model.Vendor;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.repository.PurchaseOrderRepository;
import nimblix.in.HealthCareHub.repository.VendorRepository;
import nimblix.in.HealthCareHub.request.PurchaseOrderRequest;
import nimblix.in.HealthCareHub.response.PurchaseOrderResponse;
import nimblix.in.HealthCareHub.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final VendorRepository vendorRepository;
    private final MedicineRepository medicineRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request) {

        if (request.getVendorId() == null)
            throw new RuntimeException("Vendor ID is required");

        if (request.getHospitalId() == null)
            throw new RuntimeException("Hospital ID is required");

        if (request.getQuantity() == null || request.getQuantity() <= 0)
            throw new RuntimeException("Quantity must be greater than zero");

        if (request.getMedicineName() == null || request.getMedicineName().isBlank())
            throw new RuntimeException("Medicine name cannot be empty");

        Vendor vendor = vendorRepository.findById(request.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        PurchaseOrder order = new PurchaseOrder();
        order.setMedicineName(request.getMedicineName().trim());
        order.setQuantity(request.getQuantity());
        order.setOrderDate(LocalDate.now());
        order.setStatus(HealthCareConstants.STATUS_PENDING);
        order.setVendor(vendor);
        order.setHospital(hospital);

        order = purchaseOrderRepository.save(order);

        return mapToResponse(order);
    }

    @Override
    public List<PurchaseOrderResponse> getAllPurchaseOrders() {

        return purchaseOrderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PurchaseOrderResponse getPurchaseOrderById(Long id) {

        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        return mapToResponse(order);
    }

    @Override
    public PurchaseOrderResponse approvePurchaseOrder(Long id) {

        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        if (order.getStatus().equals(HealthCareConstants.STATUS_APPROVED))
            throw new RuntimeException("Order already approved");

        if (order.getStatus().equals(HealthCareConstants.STATUS_REJECTED))
            throw new RuntimeException("Rejected order cannot be approved");

        if (order.getStatus().equals(HealthCareConstants.STATUS_COMPLETED))
            throw new RuntimeException("Completed order cannot be approved");

        order.setStatus(HealthCareConstants.STATUS_APPROVED);
        purchaseOrderRepository.save(order);

        return mapToResponse(order);
    }

    @Override
    public PurchaseOrderResponse rejectPurchaseOrder(Long id) {

        PurchaseOrder order = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        if (order.getStatus().equals(HealthCareConstants.STATUS_REJECTED))
            throw new RuntimeException("Order already rejected");

        if (order.getStatus().equals(HealthCareConstants.STATUS_APPROVED))
            throw new RuntimeException("Approved order cannot be rejected");

        if (order.getStatus().equals(HealthCareConstants.STATUS_COMPLETED))
            throw new RuntimeException("Completed order cannot be rejected");

        order.setStatus(HealthCareConstants.STATUS_REJECTED);
        purchaseOrderRepository.save(order);

        return mapToResponse(order);
    }

    @Override
    public PurchaseOrderResponse receiveGoods(Long orderId) {

        PurchaseOrder order = purchaseOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));

        if (order.getStatus().equals(HealthCareConstants.STATUS_COMPLETED))
            throw new RuntimeException("Goods already received for this order");

        if (order.getStatus().equals(HealthCareConstants.STATUS_REJECTED))
            throw new RuntimeException("Rejected order cannot receive goods");

        if (!order.getStatus().equals(HealthCareConstants.STATUS_APPROVED))
            throw new RuntimeException("Only APPROVED orders can receive goods");

        if (order.getQuantity() <= 0)
            throw new RuntimeException("Invalid order quantity");

        Medicine medicine = medicineRepository
                .findByMedicineNameAndHospital(
                        order.getMedicineName(),
                        order.getHospital()
                )
                .orElseGet(() -> {
                    Medicine m = new Medicine();
                    m.setMedicineName(order.getMedicineName());
                    m.setStockQuantity(0);
                    m.setHospital(order.getHospital());
                    return m;
                });

        medicine.setStockQuantity(
                medicine.getStockQuantity() + order.getQuantity()
        );

        medicineRepository.save(medicine);

        order.setStatus(HealthCareConstants.STATUS_COMPLETED);
        purchaseOrderRepository.save(order);

        return mapToResponse(order);
    }

    private PurchaseOrderResponse mapToResponse(PurchaseOrder order) {

        return PurchaseOrderResponse.builder()
                .id(order.getId())
                .medicineName(order.getMedicineName())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .orderDate(order.getOrderDate())
                .vendorId(order.getVendor().getVendorId())
                .vendorName(order.getVendor().getVendorName())
                .build();
    }
}