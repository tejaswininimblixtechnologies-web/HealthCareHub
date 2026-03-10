package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.BillingRequest;
import nimblix.in.HealthCareHub.response.BillingResponse;
import java.util.List;

public interface FinanceService {

    BillingResponse createBill(BillingRequest request);

    List<BillingResponse> getPatientBills(Long patientId);
}