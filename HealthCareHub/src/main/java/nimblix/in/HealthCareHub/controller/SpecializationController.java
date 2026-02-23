package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.service.SpecializationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {

    private final SpecializationService service;

    public SpecializationController(SpecializationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Specialization>> getAll() {
        return ResponseEntity.ok(service.getAllSpecializations());
    }
}