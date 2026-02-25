package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository repository;

    @Override
    public List<Prescription> getPrescriptionsByDoctor(Long doctorId) {

        List<Prescription> list = repository.findByDoctorId(doctorId);

        if (list.isEmpty()) {
            throw new RuntimeException("No prescriptions found for doctor id: " + doctorId);
        }

        return list;
    }
}