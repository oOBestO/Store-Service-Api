package com.example.Store_Service.Api.controller;

import com.example.Store_Service.Api.model.TableModel;
import com.example.Store_Service.Api.model.ReservationModel;
import com.example.Store_Service.Api.service.TableService;
import com.example.Store_Service.Api.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = "http://localhost:4200") // อนุญาตให้ Angular เชื่อมต่อ
public class TableController {

    @Autowired
    private TableService tableService;

    @Autowired
    private ReservationService reservationService;

    // ✅ สร้างโต๊ะใหม่
    @PostMapping
    public TableModel createTable(@RequestBody TableModel table) {
        return tableService.saveTable(table);
    }

    // ✅ ดึงข้อมูลโต๊ะทั้งหมด
    @GetMapping
    public List<TableModel> getAllTables() {
        List<TableModel> tables = tableService.getAllTablesWithReservations();
    
        if (tables != null && !tables.isEmpty()) {
            System.out.println("✅ Data found: " + tables.size());
        } else {
            System.out.println("⚠️ No data found");
        }

        return tables;
    }

    // ✅ ดึงข้อมูลโต๊ะตาม ID
    @GetMapping("/{id}")
    public ResponseEntity<TableModel> getTableById(@PathVariable Long id) {
        Optional<TableModel> table = tableService.getTableById(id);
        return table.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ อัปเดตข้อมูลโต๊ะ
    @PutMapping("/{id}")
    public ResponseEntity<TableModel> updateTable(@PathVariable Long id, @RequestBody TableModel table) {
        TableModel updatedTable = tableService.updateTable(id, table);
        return updatedTable != null ? ResponseEntity.ok(updatedTable) : ResponseEntity.notFound().build();
    }

    // ✅ ลบโต๊ะ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ เพิ่ม Endpoint สำหรับบันทึกการจอง
    @PostMapping("/reserve")
    public ResponseEntity<ReservationModel> reserveTable(@RequestBody ReservationModel reservation) {
        ReservationModel newReservation = reservationService.saveReservation(reservation);
        return ResponseEntity.ok(newReservation);
    }

    // ✅ ดึงข้อมูลการจองของโต๊ะที่กำหนด
    @GetMapping("/{tableId}/reservations")
    public List<ReservationModel> getReservationsByTableId(@PathVariable Long tableId) {
        return reservationService.getReservationsByTableId(tableId);
    }

    // ✅ ดึงข้อมูลโต๊ะที่ยังไม่มีการจอง
    @GetMapping("/available")
    public List<TableModel> getAvailableTables() {
    System.out.println("Fetching available tables...");
    return tableService.getAvailableTables();
    }

}
