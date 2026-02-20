package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.LabOrder;
import nimblix.in.HealthCareHub.service.LabOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab-orders")
public class LabOrderController {

   @Autowired
   private LabOrderService labOrderService;

   @PutMapping("/{id}/status")
   public ResponseEntity<LabOrder> updateLabOrderStatus(@PathVariable Long id, @RequestParam String status) {
      LabOrder updatedOrder = labOrderService.updateLabOrderStatus(id, status);
      return ResponseEntity.ok(updatedOrder);
   }
}
