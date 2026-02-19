package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.*;
import java.util.List;

public interface InsuranceService {
    InsuranceProvider addProvider(InsuranceProvider provider);
    PatientInsurance assignInsurance(PatientInsurance patientInsurance);
    boolean verifyEligibility(Long patientId);
    InsuranceClaim submitClaim(InsuranceClaim claim);
    List<InsuranceClaim> getClaimStatus(Long patientId);
}