package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.dto.HospitalRequestDTO;
import nimblix.in.HealthCareHub.dto.HospitalResponseDTO;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Override
    public HospitalResponseDTO createHospital(HospitalRequestDTO request) {

        Hospital hospital = Hospital.builder()
                .name(request.getName())
                .build();

        hospital = hospitalRepository.save(hospital);

        return HospitalResponseDTO.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .build();
    }

    @Override
    public List<HospitalResponseDTO> getAllHospitals() {

        return hospitalRepository.findAll()
                .stream()
                .map(hospital -> HospitalResponseDTO.builder()
                        .id(hospital.getId())
                        .name(hospital.getName())
                        .build())
                .collect(Collectors.toList());
    }
}