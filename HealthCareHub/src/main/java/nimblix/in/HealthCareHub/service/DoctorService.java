package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.dto.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.DoctorAvailability;

import java.util.List;

public interface DoctorService {

    Doctor createDoctor(Doctor doctor);

    Doctor registerDoctor(DoctorRegistrationRequest request);

    Doctor getDoctorById(Long doctorId);

    List<Doctor> getAllDoctors();

    List<Doctor> getDoctorsByCity(String city);

    List<Doctor> getDoctorsBySpecialization(Long specializationId);

    List<DoctorAvailability> getDoctorAvailability(Long doctorId);

    Doctor updateDoctor(Long doctorId, Doctor doctor);

    void updateDoctorStatus(Long doctorId, String status);

    void updateAvailability(Long doctorId, List<DoctorAvailability> availability);

    boolean checkEmailExists(String email);
}