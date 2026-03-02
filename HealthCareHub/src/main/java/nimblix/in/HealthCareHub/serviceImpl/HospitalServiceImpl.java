package nimblix.in.HealthCareHub.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.exception.HospitalNotFoundException;
import nimblix.in.HealthCareHub.exception.InvalidRoomStatusException;
import nimblix.in.HealthCareHub.exception.RoomNotFoundException;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.UpdateRoomStatusRequest;
import nimblix.in.HealthCareHub.response.UpdateRoomStatusResponse;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Override
    public String registerHospital(HospitalRegistrationRequest request) {

        // Check if hospital already exists
        if (hospitalRepository.findByName(request.getName()).isPresent()) {
            return "Hospital already exists";
        }

        Hospital hospital = Hospital.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .phone(request.getPhone())
                .email(request.getEmail())
                .totalBeds(request.getTotalBeds())
                .build();

        hospitalRepository.save(hospital);

        return "Hospital Registered Successfully";
    }

    @Override
    @Transactional
    public UpdateRoomStatusResponse updateRoomStatus(Long hospitalId, UpdateRoomStatusRequest request) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found"));

        if (hospital.getRooms() == null || hospital.getRooms().isEmpty()) {
            throw new RoomNotFoundException("No rooms exist for this hospital");
        }

        if (request.getRoomStatus() == null ||
                (!HealthCareConstants.AVAILABLE.equals(request.getRoomStatus()) &&
                        !HealthCareConstants.OCCUPIED.equals(request.getRoomStatus()) &&
                        !HealthCareConstants.MAINTENANCE.equals(request.getRoomStatus()))) {
            throw new InvalidRoomStatusException("Invalid room status");
        }

        Hospital.Room existingRoom = hospital.getRooms().stream()
                .filter(r -> r.getRoomNumber().equals(request.getRoomNumber()))
                .findFirst()
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        existingRoom.setRoomStatus(request.getRoomStatus());

        //Optional to update the Room Type
        if (request.getRoomType() != null && !request.getRoomType().isEmpty()) {
            if (!HealthCareConstants.ICU.equals(request.getRoomType()) &&
                    !HealthCareConstants.GENERAL.equals(request.getRoomType()) &&
                    !HealthCareConstants.PRIVATE.equals(request.getRoomType())) {
                throw new InvalidRoomStatusException("Invalid room type");
            }
            existingRoom.setRoomType(request.getRoomType());
        }

        hospitalRepository.save(hospital);


        return UpdateRoomStatusResponse.builder()
                .roomNumber(existingRoom.getRoomNumber())
                .roomType(existingRoom.getRoomType()) // existing type kept if not updated
                .roomStatus(existingRoom.getRoomStatus())
                .message("Room updated successfully")
                .build();
    }
}
