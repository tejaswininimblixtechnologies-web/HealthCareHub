package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.response.*;

public interface  DashboardService {

    DashboardSummaryResponse getDashboardSummary();

    AdmissionDischargeChartResponse getAdmissionsDischargesActivity();

    SpecializationDistributionChartResponse getSpecializationDistribution();

    SurgeryEmergencyChartResponse getSurgeriesEmergenciesActivity();

    HospitalOverviewTableResponse getHospitalOverview();


}
