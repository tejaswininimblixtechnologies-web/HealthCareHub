package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import nimblix.in.HealthCareHub.request.PrescriptionRequest;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository repository;

    @Override
    public String createPrescription(PrescriptionRequest request) {

        Prescription prescription = new Prescription();
        prescription.setMedicine(request.getMedicine());
        prescription.setDoctorId(request.getDoctorId());

        repository.save(prescription);

        return "Prescription created successfully";
    }

    @Override
    public List<PrescriptionResponse> getPrescriptionsByDoctor(Long doctorId) {

        List<Prescription> list = repository.findByDoctorId(doctorId);

        return list.stream().map(p -> {
            PrescriptionResponse res = new PrescriptionResponse();
            res.setId(p.getId());
            res.setMedicine(p.getMedicine());
            res.setDoctorId(p.getDoctorId());
            return res;
        }).toList();
    }
}