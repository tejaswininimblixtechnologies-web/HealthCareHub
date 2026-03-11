package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.constants.HealthCareConstants;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import nimblix.in.HealthCareHub.repository.HospitalRepository;
import nimblix.in.HealthCareHub.repository.PatientRepository;
import nimblix.in.HealthCareHub.response.*;
import nimblix.in.HealthCareHub.service.DashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public DashboardSummaryResponse getDashboardSummary() {

        // Query to fetch total beds
        Long totalBeds = hospitalRepository.getTotalBeds();

        // Query to fetch active doctors
        Long activeDoctors = doctorRepository.countActiveDoctors();

        // Query to fetch patients served
        Long patientsServed = patientRepository.count();

        // Query to fetch average hospital rating
        Double averageRating = hospitalRepository.getAverageRating();

        // Edge Case 1: handle null values
        if (totalBeds == null) totalBeds = 0L;
        if (activeDoctors == null) activeDoctors = 0L;
        if (patientsServed == null) patientsServed = 0L;
        if (averageRating == null) averageRating = 0.0;

        return new DashboardSummaryResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.DASHBOARD_FETCHED_SUCCESS,
                totalBeds,
                activeDoctors,
                patientsServed,
                averageRating
        );
    }


    @Override
    public AdmissionDischargeChartResponse getAdmissionsDischargesActivity() {

        // Edge Case: calculate start date for last 14 days
        LocalDate startDate = LocalDate.now().minusDays(14);

        // Query used to fetch admissions and discharges data
        List<AdmissionDischargeActivityResponse> activity =
                patientRepository.getAdmissionsDischargesLast14Days(startDate);

        // Edge Case: no activity found
        if (activity.isEmpty()) {

            return new AdmissionDischargeChartResponse(
                    HealthCareConstants.SUCCESS,
                    HealthCareConstants.NO_ACTIVITY_FOUND,
                    List.of()
            );
        }

        return new AdmissionDischargeChartResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.ACTIVITY_FETCHED_SUCCESS,
                activity
        );
    }

    @Override
    public SpecializationDistributionChartResponse getSpecializationDistribution() {

        // Query used to fetch specialization distribution
        List<SpecializationDistributionResponse> distribution =
                doctorRepository.getSpecializationDistribution();

        // Edge Case: No specialization data
        if (distribution.isEmpty()) {

            return new SpecializationDistributionChartResponse(
                    HealthCareConstants.SUCCESS,
                    HealthCareConstants.NO_SPECIALIZATION_FOUND,
                    List.of()
            );
        }

        return new SpecializationDistributionChartResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.SPECIALIZATION_FETCHED_SUCCESS,
                distribution
        );
    }

    @Override
    public SurgeryEmergencyChartResponse getSurgeriesEmergenciesActivity() {

        // Edge Case: calculate last 14 days start date
        LocalDate startDate = LocalDate.now().minusDays(14);

        // Query used to fetch surgeries and emergencies data
        List<SurgeryEmergencyActivityResponse> activity =
                patientRepository.getSurgeriesEmergenciesLast14Days(startDate);

        // Edge Case: No activity found
        if (activity.isEmpty()) {

            return new SurgeryEmergencyChartResponse(
                    HealthCareConstants.SUCCESS,
                    HealthCareConstants.NO_ACTIVITY_FOUND,
                    List.of()
            );
        }

        return new SurgeryEmergencyChartResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.ACTIVITY_FETCHED_SUCCESS,
                activity
        );
    }

    @Override
    public HospitalOverviewTableResponse getHospitalOverview() {

        // Query used to fetch hospital overview data
        List<HospitalOverviewResponse> hospitals =
                hospitalRepository.getHospitalOverview();

        // Edge Case: No hospitals found
        if (hospitals.isEmpty()) {

            return new HospitalOverviewTableResponse(
                    HealthCareConstants.SUCCESS,
                    HealthCareConstants.NO_HOSPITAL_FOUND,
                    List.of()
            );
        }

        return new HospitalOverviewTableResponse(
                HealthCareConstants.SUCCESS,
                HealthCareConstants.HOSPITAL_OVERVIEW_FETCHED_SUCCESS,
                hospitals
        );
    }
}