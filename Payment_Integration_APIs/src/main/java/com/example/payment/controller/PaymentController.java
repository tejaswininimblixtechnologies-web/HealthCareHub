package com.example.payment.controller;

import com.example.payment.model.Payment;
import com.example.payment.service.PaymentService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/pay")
    public String pay(){
        return "payment successfull";
    }

    private final PaymentService service;

    @GetMapping("/invoice/{id}")
    public void generateInvoice(@PathVariable Long id,
                                HttpServletResponse response) throws Exception {

        Payment payment = service.getAllTransactions()
                .stream().filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);

        response.setContentType("application/pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        document.add(new Paragraph("Hospital Payment Invoice"));
        document.add(new Paragraph("Order ID: " + payment.getOrderId()));
        document.add(new Paragraph("Payment ID: " + payment.getPaymentId()));
        document.add(new Paragraph("Amount: ₹" + payment.getAmount()));

        document.close();
    }

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestParam int amount) throws Exception {
        return service.createOrder(amount);
    }

    @PostMapping("/save")
    public Payment savePayment(@RequestParam String orderId,
                               @RequestParam String paymentId,
                               @RequestParam Double amount) {
        return service.savePayment(orderId, paymentId, amount);
    }

    @GetMapping("/history")
    public List<Payment> history() {
        return service.getAllTransactions();
    }


    @PostMapping("/refund")
    public String refund(@RequestParam String paymentId) throws Exception {
        service.refundPayment(paymentId);
        return "Refund Initiated Successfully";
    }
}
