//package nimblix.in.HealthCareHub.serviceImpl;
//
//import nimblix.in.HealthCareHub.model.*;
//import nimblix.in.HealthCareHub.repository.*;
//import nimblix.in.HealthCareHub.service.InsuranceService;
//import org.springframework.stereotype.Service;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class InsuranceServiceImpl implements InsuranceService {
//
//    private final ProviderRepository providerRepository;
//    private final PatientInsuranceRepository patientInsuranceRepository;
//    private final ClaimRepository claimRepository;
//
//    public InsuranceServiceImpl(ProviderRepository providerRepository,
//                                PatientInsuranceRepository patientInsuranceRepository,
//                                ClaimRepository claimRepository) {
//        this.providerRepository = providerRepository;
//        this.patientInsuranceRepository = patientInsuranceRepository;
//        this.claimRepository = claimRepository;
//    }
//
//    @Override
//    public InsuranceProvider addProvider(InsuranceProvider provider) {
//        return providerRepository.save(provider);
//    }
//
//    @Override
//    public PatientInsurance assignInsurance(PatientInsurance patientInsurance) {
//        return patientInsuranceRepository.save(patientInsurance);
//    }
//
//    @Override
//    public boolean verifyEligibility(Long patientId) {
//        Optional<PatientInsurance> insurance = patientInsuranceRepository.findByPatientId(patientId);
//        return insurance.isPresent() && insurance.get().getValidTill().isAfter(LocalDate.now());
//    }
//
//    @Override
//    public InsuranceClaim submitClaim(InsuranceClaim claim) {
//        claim.setStatus("PENDING");
//        claim.setSubmittedOn(LocalDate.now());
//        return claimRepository.save(claim);
//    }
//
//    @Override
//    public List<InsuranceClaim> getClaimStatus(Long patientId) {
//        return claimRepository.findByPatientId(patientId);
//    }
//}
//package nimblix.in.HealthCareHub.serviceImpl;
//
//import nimblix.in.HealthCareHub.model.*;
//import nimblix.in.HealthCareHub.repository.*;
//import nimblix.in.HealthCareHub.service.InsuranceService;
//
//import org.springframework.stereotype.Service;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//@Service
//public class InsuranceServiceImpl implements InsuranceService {
//
//    private final ProviderRepository providerRepository;
//    private final PatientInsuranceRepository patientInsuranceRepository;
//    private final ClaimRepository claimRepository;
//    private final InsurancePolicyRepository insurancePolicyRepository;
//
//    public InsuranceServiceImpl(ProviderRepository providerRepository,
//                                PatientInsuranceRepository patientInsuranceRepository,
//                                ClaimRepository claimRepository,
//                                InsurancePolicyRepository insurancePolicyRepository) {
//        this.providerRepository = providerRepository;
//        this.patientInsuranceRepository = patientInsuranceRepository;
//        this.claimRepository = claimRepository;
//        this.insurancePolicyRepository = insurancePolicyRepository;
//    }
//
//    @Override
//    public InsuranceProvider addProvider(InsuranceProvider provider) {
//        return providerRepository.save(provider);
//    }
//
//    @Override
//    public PatientInsurance assignInsurance(PatientInsurance patientInsurance) {
//
//        // 1️⃣ Save InsurancePolicy
//        InsurancePolicy policy = new InsurancePolicy();
//        policy.setPolicyNumber(patientInsurance.getPolicyNumber());
//        policy.setValidityDate(patientInsurance.getValidTill());
//        policy.setPatientId(patientInsurance.getPatientId());
//        policy.setProviderId(patientInsurance.getProvider().getId());
//        insurancePolicyRepository.save(policy);
//
//        // 2️⃣ Save PatientInsurance with provider and validTill
//        Optional<PatientInsurance> existing = patientInsuranceRepository.findByPatientId(patientInsurance.getPatientId());
//        PatientInsurance pi;
//        if(existing.isPresent()){
//            pi = existing.get();
//            pi.setProvider(patientInsurance.getProvider());
//            pi.setValidTill(patientInsurance.getValidTill());
//            pi.setPolicyNumber(patientInsurance.getPolicyNumber());
//        } else {
//            pi = patientInsurance;
//        }
//        return patientInsuranceRepository.save(pi);
//    }
//
//    @Override
//    public boolean verifyEligibility(Long patientId) {
//        Optional<PatientInsurance> insurance = patientInsuranceRepository.findByPatientId(patientId);
//        return insurance.isPresent() &&
//                insurance.get().getValidTill() != null &&
//                insurance.get().getValidTill().isAfter(LocalDate.now());
//    }
//
//    @Override
//    public InsuranceClaim submitClaim(InsuranceClaim claim) {
//        claim.setStatus("PENDING");
//        claim.setSubmittedOn(LocalDate.now());
//        return claimRepository.save(claim);
//    }
//
//    @Override
//    public List<InsuranceClaim> getClaimStatus(Long patientId) {
//        return claimRepository.findByPatientId(patientId);
//    }
//}

package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.*;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.service.InsuranceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final ProviderRepository providerRepository;
    private final PatientInsuranceRepository patientInsuranceRepository;
    private final ClaimRepository claimRepository;

    public InsuranceServiceImpl(
            ProviderRepository providerRepository,
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
        Optional<PatientInsurance> insurance =
                patientInsuranceRepository.findByPatientId(patientId);

        return insurance.isPresent();
    }

    @Override
    public InsuranceClaim submitClaim(InsuranceClaim claim) {
        claim.setStatus("SUBMITTED");
        return claimRepository.save(claim);
    }

    @Override
    public List<InsuranceClaim> getClaimStatus(Long patientId) {
        return claimRepository.findByPatientId(patientId);
    }
}