package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.LabResult;
import nimblix.in.HealthCareHub.repository.LabResultRepository;
import nimblix.in.HealthCareHub.service.LabResultService;
import nimblix.in.HealthCareHub.service.EmailService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabResultServiceImpl implements LabResultService {

    private final LabResultRepository labResultRepository;
    private final EmailService emailService;

    @Override
    public LabResult createLabResult(LabResult labResult) {

        LabResult saved = labResultRepository.save(labResult);

        if ("READY".equalsIgnoreCase(saved.getStatus())) {

            emailService.sendSimpleMail(
                    saved.getPatientEmail(),
                    "Lab Result Ready",
                    "Your lab test " + saved.getTestName() +
                            " is ready.\nResult: " + saved.getResult()
            );


        }

        return saved;
    }

    @Override
    public void notifyPatient(Long id) {

    }
}
