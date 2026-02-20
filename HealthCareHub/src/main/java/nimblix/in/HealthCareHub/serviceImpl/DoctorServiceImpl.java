package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.exception.SlotNotFoundException;
import nimblix.in.HealthCareHub.exception.UserNotFoundException;
import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.model.DoctorAvailability;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.DoctorAvailabilityRepository;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import nimblix.in.HealthCareHub.request.DoctorAvailabilityRequest;
import nimblix.in.HealthCareHub.request.DoctorRegistrationRequest;
import nimblix.in.HealthCareHub.service.DoctorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorAvailabilityRepository doctorAvailabilityRepository;


    @Override
    public DoctorAvailability addDoctorTimeSlot(DoctorAvailabilityRequest request) {

        if (request.getDoctorId() == null)
            throw new RuntimeException("Doctor ID cannot be null");

        if (request.getAvailableDate() == null || request.getAvailableDate().trim().isEmpty())
            throw new RuntimeException("Available date cannot be null or empty");

        if (request.getStartTime() == null || request.getStartTime().trim().isEmpty())
            throw new RuntimeException("Start time cannot be null or empty");

        if (request.getEndTime() == null || request.getEndTime().trim().isEmpty())
            throw new RuntimeException("End time cannot be null or empty");

        if (request.getIsAvailable() == null)
            throw new RuntimeException("Availability status cannot be null");


        doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new UserNotFoundException(
                        "Doctor not found with id: " + request.getDoctorId()
                ));

        boolean exists = doctorAvailabilityRepository
                .existsByDoctorIdAndAvailableDateAndStartTime(
                        request.getDoctorId(),
                        request.getAvailableDate(),
                        request.getStartTime()
                );

        if (exists) {
            throw new RuntimeException(
                    "Time slot already exists for doctor on " +
                            request.getAvailableDate() + " at " + request.getStartTime()
            );
        }

        DoctorAvailability slot = DoctorAvailability.builder()
                .doctorId(request.getDoctorId())
                .availableDate(request.getAvailableDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .isAvailable(request.getIsAvailable())
                .build();

        return doctorAvailabilityRepository.save(slot);
    }


    @Override
    public DoctorAvailability updateDoctorTimeSlot(Long slotId,
                                                   DoctorAvailabilityRequest request) {

        DoctorAvailability slot = doctorAvailabilityRepository.findById(slotId)
                .orElseThrow(() -> new SlotNotFoundException(
                        "Time slot not found with id: " + slotId
                ));

        if (request.getAvailableDate() != null && request.getStartTime() != null) {

            boolean exists = doctorAvailabilityRepository
                    .existsByDoctorIdAndAvailableDateAndStartTimeAndIdNot(
                            slot.getDoctorId(),
                            request.getAvailableDate(),
                            request.getStartTime(),
                            slotId
                    );

            if (exists) {
                throw new RuntimeException(
                        "Time slot already exists for doctor on " +
                                request.getAvailableDate() + " at " + request.getStartTime()
                );
            }
        }

        if (request.getAvailableDate() != null)
            slot.setAvailableDate(request.getAvailableDate());

        if (request.getStartTime() != null)
            slot.setStartTime(request.getStartTime());

        if (request.getEndTime() != null)
            slot.setEndTime(request.getEndTime());

        if (request.getIsAvailable() != null)
            slot.setIsAvailable(request.getIsAvailable());

        return doctorAvailabilityRepository.save(slot);
    }


    @Override
    public String registerDoctor(DoctorRegistrationRequest request) {

        Hospital hospital = hospitalRepository.findByName(request.getHospitalName())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        Specialization specialization = specializationRepository.findByName(request.getSpecialization())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        Doctor doctor = new Doctor();

        doctor.setName(request.getDoctorName());
        doctor.setPhone(request.getPhoneNo());
        doctor.setEmailId(request.getDoctorEmail());
        doctor.setPassword(request.getPassword());
        doctor.setHospitalId(hospital.getId());
        doctor.setSpecializationId(specialization.getId());

        doctorRepository.save(doctor);

        return "Doctor Registration Successful";
    }
}