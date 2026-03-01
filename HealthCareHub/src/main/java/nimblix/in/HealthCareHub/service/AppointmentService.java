package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.AppointmentResponse;

import java.util.List;

public interface AppointmentService {

    List<AppointmentResponse> getAppointments(
            String date,
            String startDate,
            String endDate
    );
}