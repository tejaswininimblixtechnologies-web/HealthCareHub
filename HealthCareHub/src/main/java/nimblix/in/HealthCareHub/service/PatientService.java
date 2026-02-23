package nimblix.in.HealthCareHub.service;




import nimblix.in.HealthCareHub.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> searchPatients(String name, String phone, String bloodGroup);

}