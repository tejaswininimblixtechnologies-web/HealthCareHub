package nimblix.in.HealthCareHub.controller1;


import nimblix.in.HealthCareHub.model1.Medicine;
import nimblix.in.HealthCareHub.service1.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    private MedicineService service1;

    // GET ALL MEDICINES (PAGINATED)
    @GetMapping("/medicines")
    public Page<Medicine> getAllMedicines(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return service1.getAllMedicines(page, size);
    }
}







