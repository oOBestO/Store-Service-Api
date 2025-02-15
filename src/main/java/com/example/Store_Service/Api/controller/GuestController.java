package com.example.Store_Service.Api.controller;

import com.example.Store_Service.Api.model.Guest;
import com.example.Store_Service.Api.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
@CrossOrigin(origins = "http://localhost:4200") // อนุญาตให้ Frontend (Angular) เชื่อมต่อ
public class GuestController {
    @Autowired
    private GuestService guestService;
    
    @GetMapping
    public List<String> getGuests() {
        return List.of("Guest 1", "Guest 2", "Guest 3"); // ✅ ทดสอบข้อมูลเบื้องต้น
    }

    @GetMapping("/{id}")
    public Optional<Guest> getGuestById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestService.saveGuest(guest);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Long id) {
        guestService.deleteGuest(id);
    }
    
}