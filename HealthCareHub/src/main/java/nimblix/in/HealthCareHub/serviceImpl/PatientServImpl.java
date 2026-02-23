package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PAtientRepository;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.Optional;

 @Service
public class PatientServImpl implements PatientService {

    private final PAtientRepository patientRepository;

    public PatientServImpl(PAtientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // ✅ Save Patient
    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

     // ✅ Get Patient By ID
     @Override
     public Optional<Patient> getPatientById(Long id) {
         return patientRepository.findById(id);
     }


 }
