package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.PurchaseOrderRequestDto;
import nimblix.in.HealthCareHub.model.PurchaseOrder;
import nimblix.in.HealthCareHub.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    // ✅ Create Purchase Order
    @PostMapping
    public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrderRequestDto dto) {
        return purchaseOrderService.createPurchaseOrder(dto);
    }

    // ✅ Get All Purchase Orders
    @GetMapping
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    // ✅ Get Purchase Order By ID
    @GetMapping("/{id}")
    public PurchaseOrder getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderService.getPurchaseOrderById(id);
    }

    // ✅ Approve Purchase Order
    @PutMapping("/approve/{id}")
    public PurchaseOrder approvePurchaseOrder(@PathVariable Long id) {
        return purchaseOrderService.approvePurchaseOrder(id);
    }

    // ✅ Reject Purchase Order
    @PutMapping("/reject/{id}")
    public PurchaseOrder rejectPurchaseOrder(@PathVariable Long id) {
        return purchaseOrderService.rejectPurchaseOrder(id);
    }

    @PutMapping("/receive/{id}")
    public PurchaseOrder receiveGoods(@PathVariable Long id) {
        return purchaseOrderService.receiveGoods(id);
    }
}