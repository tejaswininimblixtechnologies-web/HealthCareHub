package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Prescription;
import nimblix.in.HealthCareHub.response.PrescriptionResponse;
import nimblix.in.HealthCareHub.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final PrescriptionService prescriptionService;

    @GetMapping("/{doctorId}/prescriptions")
    public ResponseEntity<List<PrescriptionResponse>> getPrescriptionsByDoctor(
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(
                prescriptionService.getPrescriptionsByDoctor(doctorId)
        );
    }
}







