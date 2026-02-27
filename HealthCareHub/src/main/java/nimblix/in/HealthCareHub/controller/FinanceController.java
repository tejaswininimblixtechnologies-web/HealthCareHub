package nimblix.in.HealthCareHub.controller;

import lombok.RequiredArgsConstructor;
import nimblix.in.HealthCareHub.model.Medicine;
import nimblix.in.HealthCareHub.model.Payment;
import nimblix.in.HealthCareHub.repository.MedicineRepository;
import nimblix.in.HealthCareHub.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final PaymentRepository paymentRepository;
    private final MedicineRepository medicineRepository;

    // ✅ 1️⃣ Create Payment
    @PostMapping("/create-payment")
    public ResponseEntity<Payment> createPayment() {

        Payment payment = new Payment();

        payment.setItemName("MEDICINE BILL");
        payment.setItemType("MEDICINE");
        payment.setQuantity(0);
        payment.setUnitPrice(0.0);
        payment.setTotalAmount(0.0);
        payment.setAmount(0.0);
        payment.setPaymentStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setMedicines(new ArrayList<>());

        Payment savedPayment = paymentRepository.save(payment);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }

    // ✅ 2️⃣ Add Medicines To Payment
    @PostMapping("/add-medicines/{paymentId}")
    public ResponseEntity<Payment> addMedicinesToPayment(
            @PathVariable Long paymentId,
            @RequestBody List<Long> medicineIds) {

        // 1. Find Payment
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Payment not found with ID: " + paymentId));

        // 2. Fetch Medicines
        List<Medicine> medicines = medicineRepository.findAllById(medicineIds);

        if (medicines.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No medicines found for IDs: " + medicineIds);
        }

        // 3. Add medicines (append, not replace)
        if (payment.getMedicines() == null) {
            payment.setMedicines(new ArrayList<>());
        }

        payment.getMedicines().addAll(medicines);

        // 4. Recalculate total
        double total = payment.getMedicines()
                .stream()
                .mapToDouble(Medicine::getPrice)
                .sum();

        payment.setTotalAmount(total);
        payment.setAmount(total);
        payment.setQuantity(payment.getMedicines().size());
        payment.setItemType("MEDICINE");
        payment.setPaymentStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());

        // 5. Save
        Payment updatedPayment = paymentRepository.save(payment);

        return ResponseEntity.ok(updatedPayment);
    }
}