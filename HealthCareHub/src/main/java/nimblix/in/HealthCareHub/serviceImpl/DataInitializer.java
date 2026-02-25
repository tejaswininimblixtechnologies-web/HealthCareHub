package nimblix.in.HealthCareHub.serviceImpl;


import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Role;
import nimblix.in.HealthCareHub.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        // Seed roles if they do not exist
        if (!roleRepository.existsByName("PATIENT")) {
            roleRepository.save(Role.builder().name("PATIENT").build());
        }
        if (!roleRepository.existsByName("DOCTOR")) {
            roleRepository.save(Role.builder().name("DOCTOR").build());
        }

        System.out.println("Roles seeded successfully!");
    }
}
