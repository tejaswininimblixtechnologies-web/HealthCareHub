package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Specialization;
import nimblix.in.HealthCareHub.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    private final SpecializationRepository repository;

    public SpecializationService(SpecializationRepository repository) {
        this.repository = repository;
    }

    public List<Specialization> getAllSpecializations() {
        return repository.findAll();
    }
}