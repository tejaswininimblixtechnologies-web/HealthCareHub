package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrescriptionService {

    List<PrescriptionResponse> getPrescriptionsByDoctor(Long doctorId);
}