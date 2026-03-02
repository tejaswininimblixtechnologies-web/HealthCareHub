package nimblix.in.HealthCareHub.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.exception.HospitalNotFoundException;
import nimblix.in.HealthCareHub.exception.InvalidRoomStatusException;
import nimblix.in.HealthCareHub.exception.RoomNotFoundException;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.RoomData;
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
    public UpdateRoomStatusResponse updateRoomStatus(UpdateRoomStatusRequest request) {

        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found"));

        List<RoomData> rooms = hospital.getRooms();
        if (rooms == null || rooms.isEmpty() ) {
            throw new RoomNotFoundException("No rooms exist for this hospital");
        }

        RoomData existingRoom = rooms.stream()
                .filter(r -> r.getRoomNumber().equals(request.getRoomNumber()))
                .findFirst()
                .orElseThrow(() -> new RoomNotFoundException("Room number not found for this hospital"));

        if (request.getStatus() == null ||
                !request.getStatus().equals(HealthCareConstants.AVAILABLE) &&
                !request.getStatus().equals(HealthCareConstants.OCCUPIED) &&
                !request.getStatus().equals(HealthCareConstants.MAINTENANCE)) {
            throw new InvalidRoomStatusException("Invalid room status");
        }

        existingRoom.setStatus(request.getStatus());

        if (request.getType() != null && !request.getType().isEmpty()) {
            if (!request.getType().equals(HealthCareConstants.ICU) &&
                    !request.getType().equals(HealthCareConstants.GENERAL) &&
                    !request.getType().equals(HealthCareConstants.PRIVATE)) {
                throw new InvalidRoomStatusException("Invalid room type");
            }
            existingRoom.setType(request.getType());
        }

        hospitalRepository.save(hospital);

        return  UpdateRoomStatusResponse.builder()
                .message("Room updated successfully")
                .build();
    }
}
