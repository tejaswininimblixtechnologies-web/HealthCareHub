package nimblix.in.HealthCareHub.serviceImpl;

import nimblix.in.HealthCareHub.model.PrescriptionMedicines;
import nimblix.in.HealthCareHub.repository.PrescriptionMedicinesRepository;
import nimblix.in.HealthCareHub.response.PrescriptionMedicineResponse;
import nimblix.in.HealthCareHub.service.PrescriptionMedicinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionMedicinesServiceImpl implements PrescriptionMedicinesService {

    @Autowired
    private PrescriptionMedicinesRepository prescriptionMedicinesRepository;


    @Override
    public PrescriptionMedicineResponse<PrescriptionMedicines> createPrescriptionMedicines(PrescriptionMedicines prescriptionMedicines) {

        PrescriptionMedicineResponse<PrescriptionMedicines> prescriptionMedicineResponse= new PrescriptionMedicineResponse<PrescriptionMedicines>();
        prescriptionMedicineResponse.setStatus(HttpStatus.CREATED.value());
        prescriptionMedicineResponse.setMessage("Sucessfully Created Prescription Medicines");
        prescriptionMedicineResponse.setPrescriptionMedicines(prescriptionMedicines);

        prescriptionMedicinesRepository.save(prescriptionMedicines);

        return  prescriptionMedicineResponse;
    }
}
