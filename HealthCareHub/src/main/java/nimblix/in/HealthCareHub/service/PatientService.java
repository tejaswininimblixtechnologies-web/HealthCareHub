package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.RescheduleAppointmentRequest;
import nimblix.in.HealthCareHub.response.AppointmentResponse;

public interface PatientService {

    AppointmentResponse rescheduleAppointment(
            Long patientId,
            Long appointmentId,
            RescheduleAppointmentRequest request
    );
}