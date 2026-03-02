package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.request.NurseRequest;
import nimblix.in.HealthCareHub.response.NurseResponse;
import nimblix.in.HealthCareHub.service.NurseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurse")
@RequiredArgsConstructor
public class NurseController {

    private final NurseService nurseService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNurse(@RequestBody NurseRequest request) {
        return ResponseEntity.ok(nurseService.registerNurse(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<NurseResponse>> getAllNurses(
            @RequestParam(required = false) Long hospitalId,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(nurseService.getAllNurses(hospitalId, department, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NurseResponse> getNurseById(@PathVariable Long id) {
        return ResponseEntity.ok(nurseService.getNurseById(id));
    }
}
