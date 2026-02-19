package nimblix.in.HealthCareHub.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // ⭐ Dashboard
    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome Admin";
    }

    // ⭐ Manage hospitals
    @GetMapping("/hospitals")
    public String manageHospitals() {
        return "Admin managing hospitals";
    }

    // ⭐ Manage users
    @GetMapping("/users")
    public String manageUsers() {
        return "Admin managing users";
    }

    // ⭐ Add doctor (Method-level security example)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-doctor")
    public String addDoctor() {
        return "Doctor added by Admin";
    }

    // ⭐ Delete doctor
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-doctor/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        return "Doctor deleted with id: " + id;
    }
}
