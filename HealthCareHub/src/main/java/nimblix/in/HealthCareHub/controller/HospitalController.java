package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/register")
    public String registerHospital(@RequestBody HospitalRegistrationRequest request) {
        return hospitalService.registerHospital(request);


    }


    // GET ALL MEDICINES (PAGINATED)
    @GetMapping("/medicines")
    public List<Medicine> getAllMedicines(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    )

    {
        return hospitalService.getAllMedicines(page, size) .getContent();
    }
    
}
