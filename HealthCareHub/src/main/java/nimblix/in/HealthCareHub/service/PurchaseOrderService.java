package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.PurchaseOrderRequest;
import nimblix.in.HealthCareHub.model.PurchaseOrder;
import nimblix.in.HealthCareHub.response.PurchaseOrderResponse;

import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrderResponse createPurchaseOrder(PurchaseOrderRequest request);

    List<PurchaseOrderResponse> getAllPurchaseOrders();

    PurchaseOrderResponse getPurchaseOrderById(Long id);

    PurchaseOrderResponse approvePurchaseOrder(Long id);

    PurchaseOrderResponse rejectPurchaseOrder(Long id);

    PurchaseOrderResponse receiveGoods(Long orderId);
}