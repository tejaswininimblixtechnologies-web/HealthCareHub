package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.AppointmentRequest;
import nimblix.in.HealthCareHub.response.AppointmentResponse;

public interface AppointmentService {

    AppointmentResponse bookAppointment(AppointmentRequest request);

}