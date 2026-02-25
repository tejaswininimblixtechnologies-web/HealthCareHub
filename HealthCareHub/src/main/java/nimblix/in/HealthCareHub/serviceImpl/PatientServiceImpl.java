package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.PatientSearchRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientResponse> searchPatients(PatientSearchRequest request) {

        Specification<Patient> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getName() != null && !request.getName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }
            if (request.getPhone() != null && !request.getPhone().isEmpty()) {
                predicates.add(cb.like(root.get("phone"), "%" + request.getPhone() + "%"));
            }
            if (request.getBloodGroup() != null && !request.getBloodGroup().isEmpty()) {
                predicates.add(cb.equal(root.get("bloodGroup"), request.getBloodGroup()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<Patient> patients = patientRepository.findAll(spec);

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