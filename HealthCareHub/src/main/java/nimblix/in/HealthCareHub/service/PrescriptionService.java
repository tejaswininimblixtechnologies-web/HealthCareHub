package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public Object getPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }
}
