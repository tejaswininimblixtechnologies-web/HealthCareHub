package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository repository;

    @Override
    public List<PrescriptionResponse> getPrescriptionsByDoctor(Long doctorId) {

        List<Prescription> list = repository.findByDoctorId(doctorId);

        return list.stream()
                .map(p -> PrescriptionResponse.builder()
                        .id(p.getId())
                        .doctorId(p.getDoctor().getId())
                        .medicine(p.getMedicine())
                        .build())
                .toList();
    }
}