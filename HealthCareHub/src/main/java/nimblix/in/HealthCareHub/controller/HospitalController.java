package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;

import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.response.PaginatedMedicineResponse;
import nimblix.in.HealthCareHub.service.HospitalService;
import nimblix.in.HealthCareHub.service.MedicineService;
import org.springframework.web.bind.annotation.*;
import nimblix.in.HealthCareHub.response.MedicineResponse;


@RestController
@RequestMapping("api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final MedicineService medicineService;

    @PostMapping("/register")
    public String registerHospital(@RequestBody HospitalRegistrationRequest request) {
        return hospitalService.registerHospital(request);


    }


    // GET ALL MEDICINES (PAGINATED)
    @GetMapping("/medicines")
    public PaginatedMedicineResponse  getAllMedicines (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return medicineService.getAllMedicines(page, size);
    }
}
