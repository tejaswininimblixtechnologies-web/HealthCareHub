package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface PrescriptionService {

    PrescriptionResponse<Prescription> createPrescription(Prescription prescription);
}
