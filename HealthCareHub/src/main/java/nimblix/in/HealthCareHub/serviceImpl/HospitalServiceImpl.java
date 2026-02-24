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

@Service @RequiredArgsConstructor
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
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

    public String registerHospital(HospitalRegistrationRequest request) {

        // Check if hospital already exists
        if (hospitalRepository.findByName(request.getName()).isPresent()) {
            return "Hospital already exists";
        }

        Hospital hospital = Hospital.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .phone(request.getPhone())
                .email(request.getEmail())
                .totalBeds(request.getTotalBeds())
                .build();

        hospitalRepository.save(hospital);

        return "Hospital Registered Successfully";
    }
}
