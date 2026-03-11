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
import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl {
    @Autowired
    private PatientRepository repository;

//    public List<Patient> getAllPatients() {
//
//        return repository.findByIsDeletedFalse();
//    }

    public String softDeletePatient(Long id) {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

//        patient.setDeleted();   //  Mark as deleted
        repository.save(patient);

        return "Patient soft deleted successfully";
    }

    public Patient savePatient(Patient patient) {
        // TODO Auto-generated method stub
        return repository.save(patient);
    }

}
