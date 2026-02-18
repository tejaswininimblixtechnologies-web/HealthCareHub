package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.LabOrder;

import java.util.List;

public interface LabOrderService {
   LabOrder updateLabOrderStatus(Long id, String status);
}
