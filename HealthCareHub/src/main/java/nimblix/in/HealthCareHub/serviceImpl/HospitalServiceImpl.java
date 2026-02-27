package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.HospitalResponse;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Override
    public HospitalResponse registerHospital(HospitalRegistrationRequest request) {

        hospitalRepository.findByName(request.getName())
                .ifPresent(existing -> {
                    throw new RuntimeException("Hospital already exists");
                });

        Hospital hospital = new Hospital();
        hospital.setName(request.getName());
        hospital.setAddress(request.getAddress());
        hospital.setCity(request.getCity());
        hospital.setState(request.getState());
        hospital.setPhone(request.getPhone());
        hospital.setEmail(request.getEmail());
        hospital.setTotalBeds(request.getTotalBeds());

        Hospital savedHospital = hospitalRepository.save(hospital);

        return HospitalResponse.builder()
                .id(savedHospital.getId())
                .name(savedHospital.getName())
                .address(savedHospital.getAddress())
                .city(savedHospital.getCity())
                .state(savedHospital.getState())
                .phone(savedHospital.getPhone())
                .email(savedHospital.getEmail())
                .totalBeds(savedHospital.getTotalBeds())
                .message("Hospital Registered Successfully")
                .build();
    }
}