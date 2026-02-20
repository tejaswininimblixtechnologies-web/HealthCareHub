package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.LabOrder;
import nimblix.in.HealthCareHub.repository.LabOrderRepository;
import nimblix.in.HealthCareHub.service.LabOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabOrderServiceImpl implements LabOrderService {

   @Autowired
   private LabOrderRepository labOrderRepository;

   @Override
   public LabOrder updateLabOrderStatus(Long id, String status) {
      LabOrder labOrder = labOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lab Order not found with id: " + id));
      labOrder.setStatus(status);
      return labOrderRepository.save(labOrder);
   }

}
