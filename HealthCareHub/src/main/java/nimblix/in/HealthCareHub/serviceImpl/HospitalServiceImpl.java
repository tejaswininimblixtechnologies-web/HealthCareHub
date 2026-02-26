package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.HospitalResponse;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    // ✅ Create Hospital
    @Override
    public HospitalResponse createHospital(HospitalRegistrationRequest request) {

        Hospital hospital = Hospital.builder()
                .name(request.getName())
                .build();

        hospital = hospitalRepository.save(hospital);

        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .build();
    }

    // ✅ Get All Hospitals
    @Override
    public List<HospitalResponse> getAllHospitals() {

        return hospitalRepository.findAll()
                .stream()
                .map(hospital -> HospitalResponse.builder()
                        .id(hospital.getId())
                        .name(hospital.getName())
                        .build())
                .collect(Collectors.toList());
    }
}