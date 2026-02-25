package nimblix.in.HealthCareHub.controller;


import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService service;

    // GET ALL MEDICINES (PAGINATED)
    @GetMapping("/medicines")
    public List<Medicine> getAllMedicines(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    )

    {
        return service.getAllMedicines(page, size) .getContent();
    }
}