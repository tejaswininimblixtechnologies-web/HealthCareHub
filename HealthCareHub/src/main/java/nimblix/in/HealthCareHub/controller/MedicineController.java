package nimblix.in.HealthCareHub.controller;


import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/add")
    public Medicine addMedicine(@RequestBody Medicine medicine){

        return medicineService.addMedicine(medicine);
    }
}
