package nimblix.in.HealthCareHub.service;

import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository repository;

    public Page<Medicine> getAllMedicines(int page, int size)



    {

        return repository.findAll(PageRequest.of( page,size));

    }
}
