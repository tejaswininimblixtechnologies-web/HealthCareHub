package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public PrescriptionResponse<Prescription> createPrescription(Prescription prescription) {

             PrescriptionResponse prescriptionResponse= new PrescriptionResponse();
             prescriptionResponse.setStatus(HttpStatus.CREATED.value());
             prescriptionResponse.setMessage("Sucessfully Created");
            prescriptionResponse.setPrescription(prescription);

            prescriptionRepository.save(prescription);

            return  prescriptionResponse;
    }
}
