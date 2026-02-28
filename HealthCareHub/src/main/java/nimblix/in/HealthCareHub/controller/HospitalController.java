package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.request.MedicineAddRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/register")
    public String registerHospital(@RequestBody HospitalRegistrationRequest request) {
        return hospitalService.registerHospital(request);
    }

    @PostMapping("/{hospitalId}/medicine/add")
    public String addMedicine(@PathVariable Long hospitalId, @RequestBody MedicineAddRequest request){
        return hospitalService.addMedicine(hospitalId, request);
    }
}
