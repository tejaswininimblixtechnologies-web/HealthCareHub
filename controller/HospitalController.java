package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.request.HospitalLoginRequest;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.MedicineAddRequest;
import nimblix.in.HealthCareHub.response.*;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    //Register a new hospital in the system.
    @PostMapping("/register")
    public String registerHospital(
            @RequestBody HospitalRegistrationRequest request) {

        return hospitalService.registerHospital(request);
    }

    //Authenticate hospital admin using login credentials.
    @PostMapping("/login")
    public ResponseEntity<HospitalLoginResponse> loginHospital(@RequestBody HospitalLoginRequest request) {
        String token = hospitalService.loginHospital(request);

        HospitalLoginResponse response = HospitalLoginResponse.builder()
                .token(token)
                .message(HealthCareConstants.LOGIN_SUCCESS)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/medicine/add")
    public String addMedicine(@RequestBody MedicineAddRequest request){

        if (request == null) {
            throw new IllegalArgumentException("Request body cannot be null");
        }
        if (request.getHospitalId() == null) {
            throw new IllegalArgumentException("Hospital Id is required");
        }
        if (request.getMedicineName()==null || request.getMedicineName().trim().isEmpty()){
            throw new IllegalArgumentException("medicine name is required");
        }
        return hospitalService.addMedicine(request);
    }

    @PostMapping("/{hospitalId}/rooms")
    public String addRooms(
            @PathVariable Long hospitalId,
            @RequestBody List<HospitalRegistrationRequest.Room> rooms) {

        hospitalService.addRooms(hospitalId, rooms);
        return "Rooms added successfully";
    }

    @GetMapping("/{hospitalId}/available-rooms")
    public List<RoomResponse> getAvailableRooms(
            @PathVariable Long hospitalId) {

        return hospitalService.getAvailableRooms(hospitalId);
    }

    //Retrieve the list of all hospitals available in the system.

    @GetMapping
    public ResponseEntity<List<HospitalResponse>> getAllHospitals(){
        return ResponseEntity.ok(hospitalService.getAllHospitals());
    }

    //Fetch detailed information about a specific hospital.
    @GetMapping("/{id}")
    public ResponseEntity<HospitalDetailResponse> getHospitalById(@PathVariable Long id) {

        HospitalDetailResponse response = hospitalService.getHospitalById(id);
        return ResponseEntity.ok(response);
    }


    //Search hospitals based on hospital name

    @GetMapping("/search")
    public ResponseEntity<List<HospitalSearchResponse>> searchHospital(
            @RequestParam("name") String name) {

        List<HospitalSearchResponse> hospitals = hospitalService.searchHospitalByName(name);

        return ResponseEntity.ok(hospitals);
    }

    //Retrieve key statistics of a hospital such as total beds, total doctors, patients served, and number of specializations.

    @GetMapping("/{hospitalId}/stats")
    public ResponseEntity<HospitalStatsResponse> getHospitalStats(
            @PathVariable Long hospitalId) {

        if (hospitalId == null) {
            throw new IllegalArgumentException(HealthCareConstants.HOSPITAL_ID_NULL);
        }

        return ResponseEntity.ok(hospitalService.getHospitalStats(hospitalId));
    }


    //Sort hospitals based on rating, name, or doctor count
    @GetMapping("/sort")
    public ResponseEntity<HospitalSortResponse> sortHospitals(
            @RequestParam String sortBy) {

        HospitalSortResponse response = hospitalService.sortHospitals(sortBy);

        return ResponseEntity.ok(response);
    }

    //Fetch list of hospitals to populate the "All Hospitals" dropdown filter in the dashboard.
    @GetMapping("/list")
    public ResponseEntity<DropdownListResponse> getHospitalList() {

        DropdownListResponse response =
                hospitalService.getHospitalList();

        return ResponseEntity.ok(response);
    }

    // Filter hospitals based on specialization
    @GetMapping("/filter")
    public ResponseEntity<HospitalListResponse> filterHospitals(
            @RequestParam String specialization) {

        HospitalListResponse response =
                hospitalService.filterHospitalsBySpecialization(specialization);

        return ResponseEntity.ok(response);
    }

    // Get all reviews for a hospital
    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity<HospitalReviewListResponse> getHospitalReviews(
            @PathVariable Long hospitalId) {

        HospitalReviewListResponse response =
                hospitalService.getHospitalReviews(hospitalId);

        return ResponseEntity.ok(response);
    }



}