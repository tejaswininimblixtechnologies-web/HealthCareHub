package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.repository.LabResultRepository;
import nimblix.in.HealthCareHub.service.LabResultService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LabResultServiceImpl implements LabResultService {

    private final LabResultRepository labResultRepository;

    @Override
    public LabResult uploadLabResult(LabResult labResult) {
        labResult.setUploadedAt(LocalDateTime.now());
        return labResultRepository.save(labResult);
    }
}
