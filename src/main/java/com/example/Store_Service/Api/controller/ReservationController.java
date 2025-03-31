package com.example.Store_Service.Api.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Store_Service.Api.model.ReservationModel;
import com.example.Store_Service.Api.model.TableModel;
import com.example.Store_Service.Api.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<ReservationModel> getAllReservations() {
        return reservationService.getAllReservationsWithIndex(); // ✅ ดึงข้อมูลจาก service
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    public List<TableModel> getAvailableTables(
        @RequestParam("start") @DateTimeFormat(pattern = "HH:mm") LocalTime start,
        @RequestParam("end") @DateTimeFormat(pattern = "HH:mm") LocalTime end
    ) {
        return reservationService.getAvailableTables(start, end);
    }


}
