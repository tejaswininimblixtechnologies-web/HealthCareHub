package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.PurchaseOrderRequestDto;
import nimblix.in.HealthCareHub.model.PurchaseOrder;

import java.util.List;

    public interface PurchaseOrderService {

        PurchaseOrder createPurchaseOrder(PurchaseOrderRequestDto dto);

        List<PurchaseOrder> getAllPurchaseOrders();

        PurchaseOrder getPurchaseOrderById(Long id);

        PurchaseOrder approvePurchaseOrder(Long id);

        PurchaseOrder rejectPurchaseOrder(Long id);

        PurchaseOrder receiveGoods(Long orderId);

    }

