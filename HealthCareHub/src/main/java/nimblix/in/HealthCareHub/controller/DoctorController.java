package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;

import nimblix.in.HealthCareHub.service.PrescriptionService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final PrescriptionService prescriptionService;


    @GetMapping("/{doctorId}/prescriptions")
    public ResponseEntity<List<Prescription>> getPrescriptionsByDoctor(
            @PathVariable Long doctorId) {

        List<Prescription> prescriptions =
                prescriptionService.getPrescriptionsByDoctor(doctorId);

        return ResponseEntity.ok(prescriptions);
    }
}






