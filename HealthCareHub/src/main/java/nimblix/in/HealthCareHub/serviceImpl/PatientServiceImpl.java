package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Patient;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> searchPatients(String name, String phone, String bloodGroup) {

        if(name == null) name = "";
        if(phone == null) phone = "";
        if(bloodGroup == null) bloodGroup = "";

        return patientRepository
                .findByNameContainingIgnoreCaseAndPhoneContainingAndBloodGroupContainingIgnoreCase(
                        name,
                        phone,
                        bloodGroup
                );
    }
}