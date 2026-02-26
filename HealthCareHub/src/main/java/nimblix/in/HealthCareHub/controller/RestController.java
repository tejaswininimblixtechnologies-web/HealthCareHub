package nimblix.in.HealthCareHub.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class RestController {

        @GetMapping("/")
        public String home() {
            return "HealthCareHub Running Successfully ✅";
        }
    }

