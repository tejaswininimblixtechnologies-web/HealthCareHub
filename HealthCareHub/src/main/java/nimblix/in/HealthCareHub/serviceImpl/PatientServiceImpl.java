package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.PatientSearchRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientResponse> searchPatients(PatientSearchRequest request) {

        String name = (request.getName() == null || request.getName().trim().isEmpty())
                ? null : request.getName().trim();

        String phone = (request.getPhone() == null || request.getPhone().trim().isEmpty())
                ? null : request.getPhone().trim();

        String bloodGroup = (request.getBloodGroup() == null || request.getBloodGroup().trim().isEmpty())
                ? null : request.getBloodGroup().trim();

        List<Patient> patients = patientRepository.searchPatients(name, phone, bloodGroup);

        List<PatientResponse> responseList = new ArrayList<>();

        for (Patient p : patients) {
            PatientResponse pr = new PatientResponse();
            pr.setId(p.getId());
            pr.setName(p.getName());
            pr.setPhone(p.getPhone());
            pr.setBloodGroup(p.getBloodGroup());
            responseList.add(pr);
        }

        return responseList;
    }
}