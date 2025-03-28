package com.example.Store_Service.Api.controller;

import com.example.Store_Service.Api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // ✅ เปิด CORS ให้ Angular เรียกได้
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        if (userService.authenticate(username, password)) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("token", "test-token"); // ✅ อาจใช้ JWT แทนในภายหลัง
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
