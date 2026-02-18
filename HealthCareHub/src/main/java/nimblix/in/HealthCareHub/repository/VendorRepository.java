package nimblix.in.HealthCareHub.repository;

import nimblix.in.HealthCareHub.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VendorRepository extends JpaRepository<Vendor, Long> {

    boolean existsByEmail(String email);
}
