package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.exception.ResourceNotFoundException;
import nimblix.in.HealthCareHub.repository.AdmissionRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.request.AdmissionRequest;
import nimblix.in.HealthCareHub.response.AdmissionResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {

    private final AdmissionRepository admissionRepository;
    private final PatientRepository patientRepository;

    public PatientServiceImpl(AdmissionRepository admissionRepository, PatientRepository patientRepository) {
        this.admissionRepository = admissionRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<AdmissionResponse> getAdmissionHistory(AdmissionRequest request) {

        if(request==null){
            throw new IllegalArgumentException("Request cannot be null");
        }

        if(request.getPatientId()==null){
            throw new IllegalArgumentException("Patient id is required");
        }

        Long patientId=request.getPatientId();

        if(!patientRepository.existsById(patientId)){
            throw new ResourceNotFoundException("Patient not found with id "+patientId);
        }

        List<AdmissionResponse> admissions=
                admissionRepository.getAdmissionHistory(patientId);

        if(admissions.isEmpty()){
            throw new ResourceNotFoundException("No admission history found need to take admission first");
        }

        return admissions;
    }
}
