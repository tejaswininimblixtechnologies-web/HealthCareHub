package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.response.*;
import nimblix.in.HealthCareHub.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {


    private final DashboardService dashboardService;

    //Retrieve overall dashboard statistics
    //such as total beds, active doctors, patients served, and average rating.

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResponse> getDashboardSummary() {

        DashboardSummaryResponse response =
                dashboardService.getDashboardSummary();

        return ResponseEntity.ok(response);
    }


    // Retrieve hospital admissions and discharges activity for the last 14 days
    @GetMapping("/admissions-discharges")
    public ResponseEntity<AdmissionDischargeChartResponse>
    getAdmissionsDischargesActivity() {

        AdmissionDischargeChartResponse response =
                dashboardService.getAdmissionsDischargesActivity();

        return ResponseEntity.ok(response);
    }

    // Retrieve distribution of hospital specializations for dashboard pie chart
    @GetMapping("/specializations")
    public ResponseEntity<SpecializationDistributionChartResponse>
    getSpecializationsDistribution() {

        SpecializationDistributionChartResponse response =
                dashboardService.getSpecializationDistribution();

        return ResponseEntity.ok(response);
    }


    // Retrieve surgeries and emergency cases activity for dashboard bar chart
    @GetMapping("/surgeries-emergencies")
    public ResponseEntity<SurgeryEmergencyChartResponse>
    getSurgeriesEmergenciesActivity() {

        SurgeryEmergencyChartResponse response =
                dashboardService.getSurgeriesEmergenciesActivity();

        return ResponseEntity.ok(response);
    }

    // Retrieve hospital overview details for dashboard table
    @GetMapping("/hospital-overview")
    public ResponseEntity<HospitalOverviewTableResponse> getHospitalOverview() {

        HospitalOverviewTableResponse response =
                dashboardService.getHospitalOverview();

        return ResponseEntity.ok(response);
    }
}
