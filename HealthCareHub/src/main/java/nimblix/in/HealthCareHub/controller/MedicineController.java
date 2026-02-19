package nimblix.in.HealthCareHub.controller;


import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping("/add")
    public ResponseEntity<Medicine>saveMedicine(@RequestBody Medicine medicine){
        Medicine saved=medicineService.addMedicine(medicine);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
