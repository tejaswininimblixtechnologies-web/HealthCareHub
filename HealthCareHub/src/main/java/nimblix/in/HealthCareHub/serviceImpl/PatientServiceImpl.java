package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientResponse> searchPatients(String name, String bloodGroup, String phone) {

        // Convert empty strings to null
        name = (name != null && name.trim().isEmpty()) ? null : name;
        bloodGroup = (bloodGroup != null && bloodGroup.trim().isEmpty()) ? null : bloodGroup;
        phone = (phone != null && phone.trim().isEmpty()) ? null : phone;

        List<Patient> patients = patientRepository.searchPatients(name, bloodGroup, phone);

        return patients.stream()
                .map(PatientResponse::new) // map Patient to PatientResponse
                .collect(Collectors.toList());
    }
}