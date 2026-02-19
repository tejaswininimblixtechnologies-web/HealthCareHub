package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import nimblix.in.HealthCareHub.serviceImpl.PrescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionServiceImpl prescriptionServiceImpl;

    @PostMapping("/create")
    public PrescriptionResponse<Prescription> createPrescription(@RequestBody Prescription prescription){
            return prescriptionServiceImpl.createPrescription(prescription);
    }
}
