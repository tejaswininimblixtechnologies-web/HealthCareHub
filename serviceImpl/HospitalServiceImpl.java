package nimblix.in.HealthCareHub.serviceImpl;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.exception.HospitalNotFoundException;
import nimblix.in.HealthCareHub.exception.InvalidCredentialsException;
import nimblix.in.HealthCareHub.model.Hospital;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.request.HospitalLoginRequest;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.MedicineAddRequest;
import nimblix.in.HealthCareHub.response.*;
import nimblix.in.HealthCareHub.security.JwtUtil;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class HospitalServiceImpl implements HospitalService {


    private final HospitalRepository hospitalRepository;
    private final MedicineRepository medicineRepository;
    private final DoctorRepository doctorRepository;
    private final ReviewRepository reviewRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public String loginHospital(HospitalLoginRequest request) {

        // Null body check
        if (request == null) {
            throw new ValidationException(HealthCareConstants.REQUEST_BODY_NULL);
        }

        // Empty JSON check
        if (request.getEmail() == null && request.getPassword() == null) {
            throw new ValidationException(HealthCareConstants.REQUEST_BODY_NULL);
        }

        // Email validation
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new ValidationException(HealthCareConstants.EMAIL_REQUIRED);
        }

        // Password validation
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new ValidationException(HealthCareConstants.PASSWORD_REQUIRED);
        }

        Hospital hospital = hospitalRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new HospitalNotFoundException(HealthCareConstants.HOSPITAL_NOT_FOUND));

        // Password verification
        if (!passwordEncoder.matches(request.getPassword(), hospital.getPassword())) {
            throw new InvalidCredentialsException(HealthCareConstants.INVALID_CREDENTIALS);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                hospital.getEmail(),
                hospital.getPassword(),
                Collections.emptyList()
        );

        // Generate JWT Token
        return jwtUtil.generateToken(userDetails);
    }




    @Override
    public String registerHospital(HospitalRegistrationRequest request) {


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
                .password(passwordEncoder.encode(request.getPassword()))

                .totalBeds(request.getTotalBeds())
                .build();

        hospitalRepository.save(hospital);

        return "Hospital Registered Successfully";
    }
    @Override
    public String addMedicine(MedicineAddRequest request){

        //-edge cases---
        //--Check Hospital Exists--
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new IllegalArgumentException("Hospital Not Found"));

        if (request.getPrice()==null || request.getPrice()<=0){
            throw new IllegalArgumentException("price must be greater than 0");
        }

        if (request.getStockQuantity()==null || request.getStockQuantity()<0){
            throw new IllegalArgumentException("StockQuantity cannot be negative ");
        }

        Optional<Medicine> existing = medicineRepository.findByMedicineNameAndHospital(
                request.getMedicineName(), hospital);
        if (existing.isPresent()){
            throw new IllegalArgumentException("Medicine already exists in this hospital");
        }

        //--Create Medicine--
        Medicine medicine = Medicine.builder()
                .medicineName(request.getMedicineName())
                .manufacturer(request.getManufacturer())
                .description(request.getDescription())
                .dosage(request.getDosage())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .isActive("ACTIVE")
                .hospital(hospital)
                .build();

        //--Save medicine
        medicineRepository.save(medicine);
        return "Medicine Added Successfully";
    }



    @Override
    public void addRooms(Long hospitalId,
                         List<HospitalRegistrationRequest.Room> rooms) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));

        if (hospital.getRooms().size() + rooms.size() > hospital.getTotalBeds()) {
            throw new IllegalArgumentException("Exceeds total bed capacity");
        }

        for (HospitalRegistrationRequest.Room roomReq : rooms) {
            Hospital.Room room = new Hospital.Room();
            room.setRoomNumber(roomReq.getRoomNumber());
            room.setRoomType(roomReq.getRoomType());
            room.setAvailable(roomReq.isAvailable());

            hospital.getRooms().add(room);
        }

        hospitalRepository.save(hospital);
    }

    @Override
    public List<RoomResponse> getAvailableRooms(Long hospitalId) {

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("Hospital not found"));

        List<RoomResponse> response = new ArrayList<>();

        for (Hospital.Room room : hospital.getRooms()) {
            if (room.isAvailable()) {
                RoomResponse roomResponse = RoomResponse.builder()
                        .roomNumber(room.getRoomNumber())
                        .roomType(room.getRoomType())
                        .available(room.isAvailable())
                        .build();

                response.add(roomResponse);
            }
        }

        return response;
    }


        @Override
        public List<HospitalResponse> getAllHospitals() {
            return hospitalRepository.getAllHospitals();
        }



    @Override
    public HospitalDetailResponse getHospitalById(Long id) {
        log.info("Fetching hospital with id: {}", id);

        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException(id));

        log.info("Hospital fetched successfully with id: {}", id);

        return HospitalDetailResponse.builder()
                .status(HealthCareConstants.STATUS_SUCCESS)
                .message(HealthCareConstants.HOSPITAL_FETCHED_SUCCESSFULLY)
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .city(hospital.getCity())
                .state(hospital.getState())
                .phone(hospital.getPhone())
                .email(hospital.getEmail())
                .totalBeds(hospital.getTotalBeds())
                .createdTime(hospital.getCreatedTime())
                .updatedTime(hospital.getUpdatedTime())
                .build();
    }


    @Override
    public List<HospitalSearchResponse> searchHospitalByName(String name){

        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(HealthCareConstants.HOSPITAL_NAME_EMPTY);
        }

        List<Hospital> hospitals =
                hospitalRepository.findByNameContainingIgnoreCase(name);

        return hospitals.stream()
                .map(h -> HospitalSearchResponse.builder()
                        .id(h.getId())
                        .hospitalName(h.getName())
                        .city(h.getCity())
                        .state(h.getState())
                        .build())
                .toList();
    }

    @Override
    public HospitalStatsResponse getHospitalStats(Long hospitalId) {

        if (hospitalId == null) {
            throw new IllegalArgumentException(HealthCareConstants.HOSPITAL_ID_NULL);
        }

        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() ->
                        new IllegalArgumentException(HealthCareConstants.HOSPITAL_NOT_FOUND));

        Long totalDoctors = doctorRepository.countDoctorsByHospitalId(hospitalId);
        Long totalPatients = patientRepository.countPatientsByHospitalId(hospitalId);
        Long totalSpecializations = doctorRepository.countSpecializationsByHospitalId(hospitalId);

        Long totalBeds = hospital.getTotalBeds() == null
                ? 0L
                : hospital.getTotalBeds().longValue();

        Long patientsServed = totalPatients == null ? 0L : totalPatients;

        return HospitalStatsResponse.builder()
                .hospitalId(hospitalId)
                .totalBeds(totalBeds)
                .totalDoctors(totalDoctors == null ? 0L : totalDoctors)
                .totalPatients(totalPatients == null ? 0L : totalPatients)
                .patientsServed(patientsServed)
                .totalSpecializations(totalSpecializations == null ? 0L : totalSpecializations)
                .build();
    }


    @Override
    public HospitalSortResponse sortHospitals(String sortBy) {

        // Edge Case 1: sortBy null or empty
        if (sortBy == null || sortBy.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    HealthCareConstants.INVALID_SORT_PARAMETER
            );
        }

        List<Hospital> hospitals;

        switch (sortBy.toLowerCase()) {

            case "rating":
                hospitals = hospitalRepository
                        .findAll(Sort.by(Sort.Direction.DESC, "rating"));
                break;

            case "name":
                hospitals = hospitalRepository
                        .findAll(Sort.by(Sort.Direction.ASC, "name"));
                break;

            case "doctorcount":
                hospitals = hospitalRepository
                        .findAll(Sort.by(Sort.Direction.DESC, "doctorCount"));
                break;

            // Edge Case 2: invalid sort parameter
            default:
                throw new IllegalArgumentException(
                        HealthCareConstants.INVALID_SORT_PARAMETER
                );
        }

        // Edge Case 3: no hospitals found
        if (hospitals.isEmpty()) {
            return new HospitalSortResponse(
                    HealthCareConstants.SUCCESS,
                    HealthCareConstants.NO_HOSPITALS_FOUND,
                    List.of()
            );
        }

        List<HospitalSummaryResponse> hospitalResponses = hospitals.stream()
                .map(hospital -> new HospitalSummaryResponse(
                        hospital.getId(),
                        hospital.getName(),
                        hospital.getCity(),
                        hospital.getRating(),
                        hospital.getDoctorCount()
                ))
                .toList();

        return new HospitalSortResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.HOSPITALS_SORTED_SUCCESS,
                hospitalResponses
        );


    }

    @Override
    public DropdownListResponse getHospitalList() {

        // Query to fetch hospital dropdown list
        List<DropdownResponse> hospitals =
                hospitalRepository.getHospitalDropdown();

        // Edge Case: no hospitals
        if (hospitals.isEmpty()) {

            return new DropdownListResponse(
                    HealthCareConstants.SUCCESS,
                    HealthCareConstants.NO_HOSPITALS_FOUND,
                    List.of()
            );
        }

        return new DropdownListResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.HOSPITAL_LIST_FETCHED_SUCCESS,
                hospitals
        );
    }

    @Override
    public HospitalListResponse filterHospitalsBySpecialization(String specialization) {

        // Edge Case 1: specialization validation
        if (specialization == null || specialization.trim().isEmpty()) {

            return new HospitalListResponse(
                    HealthCareConstants.FAILED,
                    HealthCareConstants.INVALID_SPECIALIZATION,
                    List.of()
            );
        }

        // Query used to fetch hospitals for given specialization
        List<HospitalSpecializationResponse> hospitals =
                hospitalRepository.findHospitalsBySpecialization(specialization);

        // Edge Case 2: no hospitals found
        if (hospitals.isEmpty()) {

            return new HospitalListResponse(
                    HealthCareConstants.SUCCESS,
                    HealthCareConstants.NO_HOSPITAL_FOUND,
                    List.of()
            );
        }

        return new HospitalListResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.HOSPITAL_FETCHED_SUCCESS,
                hospitals
        );
    }


    @Override
    public HospitalReviewListResponse getHospitalReviews(Long hospitalId) {

        if (hospitalId == null || hospitalId <= 0) {
            throw new IllegalArgumentException(HealthCareConstants.INVALID_HOSPITAL_ID);
        }

        if (!hospitalRepository.existsById(hospitalId)) {
            throw new IllegalArgumentException(
                    HealthCareConstants.HOSPITAL_NOT_FOUND + hospitalId);
        }

        List<ReviewResponse> reviews =
                reviewRepository.findPatientReviewsByHospitalId(hospitalId);

        return new HospitalReviewListResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.REVIEWS_FETCHED_SUCCESS,
                reviews
        );
    }


}