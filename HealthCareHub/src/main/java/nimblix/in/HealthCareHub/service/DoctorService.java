package nimblix.in.HealthCareHub.service;


import nimblix.in.HealthCareHub.model.DoctorAvailability;
import nimblix.in.HealthCareHub.request.DoctorAvailabilityRequest;

public interface DoctorService {

    DoctorAvailability addDoctorTimeSlot(DoctorAvailabilityRequest request);

    DoctorAvailability updateDoctorTimeSlot(Long slotId, DoctorAvailabilityRequest request);
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;

public interface DoctorService {
    String RegisterDoctor(DoctorRegistrationRequest doctorRegistrationRequest);
}
