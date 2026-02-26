package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.HospitalRegistrationRequest;
import nimblix.in.HealthCareHub.service.HospitalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nimblix.in.HealthCareHub.request.UserFilterRequest;
import nimblix.in.HealthCareHub.response.UserResponse;
import nimblix.in.HealthCareHub.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hospital")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;
    private final UserService userService;


    @PostMapping("/register")
    public String registerHospital(@RequestBody HospitalRegistrationRequest request) {
        return hospitalService.registerHospital(request);
    }
    @GetMapping("/users")
    public Page<UserResponse> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            UserFilterRequest request) {

        return userService.getUsers(page, size, sortBy, direction, request);
    }
}