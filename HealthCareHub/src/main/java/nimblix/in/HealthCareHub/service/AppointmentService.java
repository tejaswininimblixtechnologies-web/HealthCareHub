package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.AppointmentResponseDTO;
import nimblix.in.HealthCareHub.service.AppointmentService;

import java.util.List;

public interface AppointmentService {

    // Task 170 - Get all appointments for a patient
    List<AppointmentResponseDTO> getAppointmentsByPatient(Long patientId);
}