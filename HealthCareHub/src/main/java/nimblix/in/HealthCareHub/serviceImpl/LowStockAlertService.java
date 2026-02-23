package nimblix.in.HealthCareHub.serviceImpl;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.service.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LowStockAlertService {

    private final MedicineRepository medicineRepository;
    private final EmailService emailService;

    private static final String ADMIN_EMAIL = "chethankumargs770@gmail.com";

    public void checkLowStock() {

        List<Medicine> lowStockMedicines =
                medicineRepository.findByQuantityLessThan(10);

        for (Medicine medicine : lowStockMedicines) {

            String subject = "Low Stock Alert - HealthCareHub";

            String body = "Medicine: " + medicine.getName() +
                    "\nCurrent Stock: " + medicine.getQuantity() +
                    "\nThreshold: " + medicine.getThreshold() +
                    "\n\nPlease restock immediately.";

            emailService.sendSimpleMail(ADMIN_EMAIL, subject, body);
        }
    }
}
