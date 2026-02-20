package com.example.payment.service;

import com.example.payment.model.Payment;
import com.example.payment.repository.PaymentRepository;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public String createOrder(int amount) throws Exception {
        RazorpayClient client = new RazorpayClient(key, secret);

        JSONObject request = new JSONObject();
        request.put("amount", amount * 100);
        request.put("currency", "INR");

        Order order = client.orders.create(request);
        return order.toString();
    }

    public Payment savePayment(String orderId, String paymentId, Double amount) {
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setPaymentId(paymentId);
        payment.setAmount(amount);
        payment.setStatus("SUCCESS");

        return repository.save(payment);
    }

    public List<Payment> getAllTransactions() {
        return repository.findAll();
    }

    public void refundPayment(String paymentId) throws Exception {
        RazorpayClient client = new RazorpayClient(key, secret);
        com.razorpay.Payment payment = client.payments.fetch(paymentId);
        JSONObject options = new JSONObject();
        options.put("amount",100);
        client.payments.refund(paymentId,options);
    }
}
