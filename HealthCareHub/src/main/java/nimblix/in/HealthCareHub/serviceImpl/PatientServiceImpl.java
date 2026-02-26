package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<PatientResponse> searchPatients(String name, String bloodGroup, String phone) {
        List<Patient> patients = patientRepository
                .findByNameContainingIgnoreCaseOrBloodGroupContainingIgnoreCaseOrPhoneContainingIgnoreCase(
                        name, bloodGroup, phone
                );

        return patients.stream()
                .map(PatientResponse::new)
                .collect(Collectors.toList());
    }
}