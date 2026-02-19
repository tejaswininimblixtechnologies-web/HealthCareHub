package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.MedicalReport;
import nimblix.in.HealthCareHub.repository.MedicalReportRepository;
import nimblix.in.HealthCareHub.service.MedicalReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalReportServiceImpl implements MedicalReportService {

    private final MedicalReportRepository medicalReportRepository;

    @Override
    public List<MedicalReport> getReportsByPatient(Long patientId) {
        return medicalReportRepository.findByPatientId(patientId);
    }
}

