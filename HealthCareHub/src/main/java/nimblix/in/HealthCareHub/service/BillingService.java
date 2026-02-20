package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.BillResponse;
import java.util.List;

public interface BillingService {
    List<BillResponse> getBillsByPatient(Long patientId);
}