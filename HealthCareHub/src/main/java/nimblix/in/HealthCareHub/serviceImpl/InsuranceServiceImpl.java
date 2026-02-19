package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.service.InsuranceService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final ProviderRepository providerRepository;
    private final PatientInsuranceRepository patientInsuranceRepository;
    private final ClaimRepository claimRepository;

    public InsuranceServiceImpl(ProviderRepository providerRepository,
                                PatientInsuranceRepository patientInsuranceRepository,
                                ClaimRepository claimRepository) {
        this.providerRepository = providerRepository;
        this.patientInsuranceRepository = patientInsuranceRepository;
        this.claimRepository = claimRepository;
    }

    @Override
    public InsuranceProvider addProvider(InsuranceProvider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public PatientInsurance assignInsurance(PatientInsurance patientInsurance) {
        return patientInsuranceRepository.save(patientInsurance);
    }

    @Override
    public boolean verifyEligibility(Long patientId) {
        Optional<PatientInsurance> insurance = patientInsuranceRepository.findByPatientId(patientId);
        return insurance.isPresent() && insurance.get().getValidTill().isAfter(LocalDate.now());
    }

    @Override
    public InsuranceClaim submitClaim(InsuranceClaim claim) {
        claim.setStatus("PENDING");
        claim.setSubmittedOn(LocalDate.now());
        return claimRepository.save(claim);
    }

    @Override
    public List<InsuranceClaim> getClaimStatus(Long patientId) {
        return claimRepository.findByPatientId(patientId);
    }
}