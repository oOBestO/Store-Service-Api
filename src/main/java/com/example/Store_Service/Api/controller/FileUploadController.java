package com.example.Store_Service.Api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    private static final String UPLOAD_DIR = "C:\\Users\\natta\\Downloads\\imgStoreService\\"; // ✅ กำหนด Path ที่ถูกต้อง

    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // ✅ ตั้งชื่อไฟล์ใหม่ (UUID) เพื่อป้องกันชื่อซ้ำ
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);

            // ✅ ตรวจสอบโฟลเดอร์ ถ้ายังไม่มีให้สร้างใหม่
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // ✅ บันทึกไฟล์ลงเซิร์ฟเวอร์
            file.transferTo(filePath.toFile());

            // ✅ ส่ง JSON กลับไปแทนข้อความเปล่า
            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", "http://localhost:8888/api/images/" + fileName);
            return ResponseEntity.ok(response); // ✅ ส่ง JSON ที่ถูกต้อง

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}