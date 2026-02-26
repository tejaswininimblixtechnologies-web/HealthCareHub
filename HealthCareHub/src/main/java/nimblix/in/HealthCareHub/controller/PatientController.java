package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.request.PatientSearchRequest;
import nimblix.in.HealthCareHub.response.PatientResponse;
import nimblix.in.HealthCareHub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // GET search (URL params)
    @GetMapping("/search")
    public List<PatientResponse> searchPatientsGet(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String phone
    ) {
        return patientService.searchPatients(name, bloodGroup, phone);
    }

    // POST search (JSON body)
    @PostMapping("/search")
    public List<PatientResponse> searchPatientsPost(@RequestBody PatientSearchRequest request) {
        return patientService.searchPatients(
                request.getName(),
                request.getBloodGroup(),
                request.getPhone()
        );
    }
}