package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Appointment;
import nimblix.in.HealthCareHub.repository.AppointmentRepository;
import nimblix.in.HealthCareHub.response.AppointmentResponse;
import nimblix.in.HealthCareHub.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponse> getAppointments(
            String date,
            String startDate,
            String endDate) {

        List<Appointment> appointments;

        // ✅ Case 1: Single Date
        if (date != null && !date.isEmpty()) {

            if ((startDate != null && !startDate.isEmpty()) ||
                    (endDate != null && !endDate.isEmpty())) {

                throw new IllegalArgumentException(
                        "Provide either 'date' OR 'startDate & endDate', not both.");
            }

            appointments = appointmentRepository
                    .findByAppointmentDate(date);
        }

        // ✅ Case 2: Date Range
        else if (startDate != null && endDate != null &&
                !startDate.isEmpty() && !endDate.isEmpty()) {

            if (startDate.compareTo(endDate) > 0) {
                throw new IllegalArgumentException(
                        "Start date cannot be after end date");
            }

            appointments = appointmentRepository
                    .findByAppointmentDateBetween(startDate, endDate);
        }

        // ❌ Invalid Case
        else {
            throw new IllegalArgumentException(
                    "Provide either 'date' OR 'startDate & endDate'");
        }

        // ✅ Mapping (No Doctor Access)
        return appointments.stream()
                .map(appointment -> AppointmentResponse.builder()
                        .id(appointment.getId())
                        .appointmentDate(appointment.getAppointmentDate())
                        .appointmentTime(appointment.getAppointmentTime())
                        .status(appointment.getStatus())
                        .build())
                .toList();
    }
}