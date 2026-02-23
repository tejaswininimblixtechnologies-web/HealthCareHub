package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SpecializationController {

    private final SpecializationRepository specializationRepository;

    // CREATE
    @PostMapping
    public Specialization create(@RequestBody Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    // GET ALL
    @GetMapping
    public List<Specialization> getAll() {
        return specializationRepository.findAll();
    }
}