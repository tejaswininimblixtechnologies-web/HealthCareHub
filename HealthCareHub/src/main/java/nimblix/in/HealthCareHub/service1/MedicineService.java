package nimblix.in.HealthCareHub.service1;

import nimblix.in.HealthCareHub.model1.Medicine;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
        import org.springframework.stereotype.Service;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repository;

    public Page<Medicine> getAllMedicines(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable);
    }
}
