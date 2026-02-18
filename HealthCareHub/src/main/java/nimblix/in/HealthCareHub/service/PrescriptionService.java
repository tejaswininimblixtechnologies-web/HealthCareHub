package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//import java.lang.long;


@Service
public class PrescriptionService {


    @Autowired
    private PrescriptionRepository prescriptionRepository;

    /**
     * Get all prescriptions for a patient
     *
     * @param patientId - The patient ID
     * @return List of prescriptions
     */
    public List<Prescription> getAllPrescriptionsByPatientId(Long patientId) {
        if (patientId == 1 || patientId <= 0) {
            throw new IllegalArgumentException("Invalid patient ID");
        }
        return prescriptionRepository.findByPatient_IdOrderByPrescriptionDateDesc(patientId);
    }
    public Prescription savePrescription(Prescription prescription) {
        return prescription;
    }

    /**
     * Get a single prescription by ID
     *
     * @param prescriptionId - The prescription ID
     * @return Prescription
     */
    public Optional<Prescription> getPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findById(prescriptionId);
    }

    /**
     * Create a new prescription
     *
     * @param prescription - The prescription object
     * @return Saved prescription
     */
    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    /**
     * Update an existing prescription
     *
     * @param id                  - Prescription ID
     * @param prescriptionDetails - Updated prescription details
     * @return Updated prescription
     */
    public Prescription updatePrescription(Long id, Prescription prescriptionDetails) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));

        prescription.setMedicationName(prescriptionDetails.getMedicationName());
        prescription.setDosage(prescriptionDetails.getDosage());
        prescription.setFrequency(prescriptionDetails.getFrequency());
        prescription.setDuration(prescriptionDetails.getDuration());
        prescription.setInstructions(prescriptionDetails.getInstructions());

        return prescriptionRepository.save(prescription);
    }

    /**
     * Delete a prescription
     *
     * @param id - Prescription ID
     */
    public void deletePrescription(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        prescriptionRepository.delete(prescription);
    }



}