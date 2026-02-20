package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @GetMapping("/doctor/{doctorId}")
    public List<Prescription> getPrescriptionsByDoctor(
            @PathVariable Long doctorId) {

        return prescriptionService.getPrescriptionsByDoctor(doctorId);
    }
}

