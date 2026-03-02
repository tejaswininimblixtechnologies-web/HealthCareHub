package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Nurse;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.NurseRepository;
import nimblix.in.HealthCareHub.request.NurseRequest;
import nimblix.in.HealthCareHub.response.NurseResponse;
import nimblix.in.HealthCareHub.service.NurseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NurseServiceImpl implements NurseService {

    private final NurseRepository nurseRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public String registerNurse(NurseRequest request) {
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Nurse nurse = new Nurse();
        nurse.setName(request.getName());
        nurse.setPhone(request.getPhone());
        nurse.setEmail(request.getEmail());
        nurse.setExperienceYears(request.getExperienceYears());
        nurse.setDepartment(request.getDepartment());
        nurse.setHospital(hospital);

        nurseRepository.save(nurse);
        return "Nurse registered successfully";
    }

    @Override
    public List<NurseResponse> getAllNurses(Long hospitalId, String department, String name) {
        List<Nurse> nurses;

        if (hospitalId != null) {
            nurses = nurseRepository.findByHospitalId(hospitalId);
        } else if (department != null && !department.isEmpty()) {
            nurses = nurseRepository.findByDepartment(department);
        } else if (name != null && !name.isEmpty()) {
            nurses = nurseRepository.findByNameContainingIgnoreCase(name);
        } else {
            nurses = nurseRepository.findAll();
        }

        return nurses.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NurseResponse getNurseById(Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nurse not found"));
        return mapToResponse(nurse);
    }

    private NurseResponse mapToResponse(Nurse nurse) {
        NurseResponse response = new NurseResponse();
        response.setId(nurse.getId());
        response.setName(nurse.getName());
        response.setPhone(nurse.getPhone());
        response.setEmail(nurse.getEmail());
        response.setExperienceYears(nurse.getExperienceYears());
        response.setDepartment(nurse.getDepartment());
        response.setHospitalName(nurse.getHospital() != null ? nurse.getHospital().getName() : null);
        return response;
    }
}
