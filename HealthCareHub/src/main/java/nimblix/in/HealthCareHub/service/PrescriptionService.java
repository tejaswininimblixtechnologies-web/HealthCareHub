package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository repository;

    public List<Prescription> getPrescriptionsByDoctor(Long doctorId) {

        List<Prescription> list = repository.findByDoctorId(doctorId);

        if (list.isEmpty()) {
            throw new RuntimeException("No prescriptions found for doctor id: " + doctorId);
        }

        return list;
    }
}
