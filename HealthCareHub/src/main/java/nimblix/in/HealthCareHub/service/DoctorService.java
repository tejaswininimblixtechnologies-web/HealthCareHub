package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.model.Doctor;

import java.time.LocalDateTime;
import java.util.List;

public interface DoctorService {

    // Get all doctors
    List<Doctor> getAllDoctors();

    // Create doctor
    Doctor saveDoctor(Doctor doctor);

    // Reschedule appointment
    Appointment rescheduleAppointment(Long appointmentId,
                                      LocalDateTime newDateTime);
}