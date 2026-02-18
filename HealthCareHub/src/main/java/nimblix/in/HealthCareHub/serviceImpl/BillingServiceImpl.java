package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Bill;
import nimblix.in.HealthCareHub.repository.BillRepository;
import nimblix.in.HealthCareHub.response.BillResponse;
import nimblix.in.HealthCareHub.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<BillResponse> getBillsByPatient(Long patientId) {
        List<Bill> bills = billRepository.findByPatientId(patientId);

        return bills.stream()
                .map(b -> new BillResponse(
                        b.getAmount(),
                        b.getDescription(),
                        b.getBillDate()))
                .collect(Collectors.toList());
    }
}