package nimblix.in.HealthCareHub.controller;

import nimblix.in.HealthCareHub.model.Doctor;
import nimblix.in.HealthCareHub.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor){
        return doctorRepository.save(doctor);
    }




































    /*
Json object:
key and value pair

{
"name": "tejaswini",
"mobile number":"8937483454",
"date":"10-05-2026",
}

*/


}
