package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.repository.*;
import nimblix.in.HealthCareHub.response.DashboardResponse;
import nimblix.in.HealthCareHub.service.DashboardService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public DashboardResponse getDashboardStats() {

        Long totalPatients = patientRepository.count();
        Long totalDoctors = doctorRepository.count();
        Double totalRevenue = paymentRepository.getTotalRevenue();

        return DashboardResponse.builder()
                .totalPatients(totalPatients)
                .totalDoctors(totalDoctors)
                .totalRevenue(totalRevenue)
                .build();
    }
}