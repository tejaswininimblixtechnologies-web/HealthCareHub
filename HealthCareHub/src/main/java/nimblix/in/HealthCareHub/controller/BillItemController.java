package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.service.BillItemService;
import nimblix.in.HealthCareHub.model.BillItem;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillItemController {

    private final BillItemService billItemService;

    @PostMapping("/{billId}/items")
    public BillItem addItem(
            @PathVariable Long billId,
            @RequestBody BillItem item) {

        return billItemService.addItemToBill(billId, item);
    }

    @GetMapping("/{billId}/items")
    public List<BillItem> getItems(@PathVariable Long billId) {
        return billItemService.getItemsByBill(billId);
    }
}