package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.PrescriptionMedicines;
import nimblix.in.HealthCareHub.response.PrescriptionMedicineResponse;

public interface PrescriptionMedicinesService {

    PrescriptionMedicineResponse<PrescriptionMedicines> createPrescriptionMedicines(PrescriptionMedicines prescriptionMedicines);
}
