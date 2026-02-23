package nimblix.in.HealthCareHub.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nimblix.in.HealthCareHub.dto.HospitalDropdownDTO;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public String saveHospital(Hospital hospital) {

        if (hospitalRepository.findByNameIgnoreCase(hospital.getName()).isPresent()) {
            return "Hospital already exists!";
        }

        hospitalRepository.save(hospital);
        return "Hospital saved successfully";
    }

    @Override
    public List<HospitalDropdownDTO> getHospitalList() {
        return hospitalRepository.getHospitalDropdown();
    }
}