package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public List<Prescription> getPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }
}