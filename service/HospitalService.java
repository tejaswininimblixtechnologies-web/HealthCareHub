package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.request.HospitalLoginRequest;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;

import nimblix.in.HealthCareHub.request.MedicineAddRequest;
import nimblix.in.HealthCareHub.response.*;

import java.util.List;

public interface HospitalService {

    String registerHospital(HospitalRegistrationRequest request);

    String addMedicine(MedicineAddRequest request);

    void addRooms(Long hospitalId, List<HospitalRegistrationRequest.Room> rooms);

    List<RoomResponse> getAvailableRooms(Long hospitalId);

    List<HospitalResponse> getAllHospitals();

    HospitalDetailResponse getHospitalById(Long id);



    List<HospitalSearchResponse> searchHospitalByName(String name);

    HospitalStatsResponse getHospitalStats(Long hospitalId);

    String loginHospital(HospitalLoginRequest request);

    HospitalSortResponse sortHospitals(String sortBy);

    DropdownListResponse getHospitalList();

    HospitalListResponse filterHospitalsBySpecialization(String specialization);

    HospitalReviewListResponse getHospitalReviews(Long hospitalId);



}
