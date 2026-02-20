package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.serviceImpl.EmergencyAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alert")
@RequiredArgsConstructor
public class EmergencyAlertController {

    private final EmergencyAlertService emergencyAlertService;

    @PostMapping("/send")
    public ResponseEntity<String> sendAlert(
            @RequestParam String message
    ) {

        emergencyAlertService.sendAlert(message);

        return ResponseEntity.ok("Emergency alert sent to admin!");
    }
}
