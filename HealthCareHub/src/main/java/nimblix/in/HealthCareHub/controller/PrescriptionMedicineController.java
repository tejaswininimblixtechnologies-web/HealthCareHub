package nimblix.in.HealthCareHub.controller;


import nimblix.in.HealthCareHub.model.PrescriptionMedicines;
import nimblix.in.HealthCareHub.response.PrescriptionMedicineResponse;
import nimblix.in.HealthCareHub.serviceImpl.PrescriptionMedicinesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PrescriptionMedicine")
public class PrescriptionMedicineController {

    @Autowired
    private PrescriptionMedicinesServiceImpl prescriptionMedicinesServiceImpl;

    @PostMapping("/create")
    public PrescriptionMedicineResponse<PrescriptionMedicines> createprescriptionMedicines(@RequestBody PrescriptionMedicines prescriptionMedicines){
        return prescriptionMedicinesServiceImpl.createPrescriptionMedicines(prescriptionMedicines);

    }
}
