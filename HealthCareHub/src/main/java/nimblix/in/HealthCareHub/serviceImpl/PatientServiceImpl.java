package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.repository.UserRepository;
import nimblix.in.HealthCareHub.response.PatientSearchResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final UserRepository userRepository;

    @Override
    public List<PatientSearchResponse> searchPatients(String name, String phone, String bloodGroup) {

        return userRepository.searchPatients(name, phone);

    }
}