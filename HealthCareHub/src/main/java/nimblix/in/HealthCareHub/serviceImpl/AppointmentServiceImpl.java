package nimblix.in.HealthCareHub.service.impl;

import lombok.RequiredArgsConstructor;

import nimblix.in.HealthCareHub.model.Appointment;

import nimblix.in.HealthCareHub.repository.AppointmentRepository;

import nimblix.in.HealthCareHub.service.AppointmentService;

import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor

public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override

    public Appointment bookAppointment(Appointment appointment) {

        // Set default status if not provided

        if (appointment.getStatus() == null || appointment.getStatus().isEmpty()) {

            appointment.setStatus("BOOKED");

        }

        // Save appointment

        return appointmentRepository.save(appointment);

    }

}
