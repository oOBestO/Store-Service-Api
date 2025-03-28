package com.example.Store_Service.Api.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PaymentController {

    // ✅ จำลองฐานข้อมูลเก็บสถานะการชำระเงิน
    private Map<Integer, String> paymentStatus = new HashMap<>();

    @PostMapping("/confirm/{orderId}")
    public ResponseEntity<String> confirmPayment(@PathVariable int orderId) {
        // ✅ เมื่อผู้ใช้กด "ยืนยันชำระเงิน" ให้กำหนดสถานะเป็น "pending"
        paymentStatus.put(orderId, "pending");
        return ResponseEntity.ok("กำลังรอการชำระเงิน...");
    }

    @GetMapping("/status/{orderId}")
    public ResponseEntity<Map<String, String>> checkPaymentStatus(@PathVariable int orderId) {
        // ✅ คืนค่าสถานะการชำระเงิน (เช่น "pending", "paid", "failed")
        String status = paymentStatus.getOrDefault(orderId, "not_found");

        Map<String, String> response = new HashMap<>();
        response.put("orderId", String.valueOf(orderId));
        response.put("status", status);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update/{orderId}")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable int orderId, @RequestParam String status) {
        // ✅ อัปเดตสถานะการชำระเงิน
        paymentStatus.put(orderId, status);
        return ResponseEntity.ok("อัปเดตสถานะการชำระเงินเป็น: " + status);
    }
}
