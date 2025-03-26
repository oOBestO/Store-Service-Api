package com.example.Store_Service.Api.service;

import com.example.Store_Service.Api.model.TableModel;
import com.example.Store_Service.Api.repository.TableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    // บันทึกโต๊ะใหม่
    public TableModel saveTable(TableModel table) {
        return tableRepository.save(table);
    }

    // ดึงข้อมูลโต๊ะทั้งหมด
    public List<TableModel> getAllTables() {
        return tableRepository.findAllTables(); // ✅ ตรวจสอบว่าไม่มีปัญหาจากฐานข้อมูล
    }

    // ดึงข้อมูลโต๊ะตาม ID
    public Optional<TableModel> getTableById(Long id) {
        return tableRepository.findById(id);
    }

    // อัปเดตข้อมูลโต๊ะ
    public TableModel updateTable(Long id, TableModel tableData) {
        // ตรวจสอบว่าโต๊ะที่ต้องการอัปเดตมีอยู่หรือไม่
        if (tableRepository.existsById(id)) {
            tableData.setId(id);  // กำหนด id ให้ตรงกับโต๊ะที่ต้องการอัปเดต
            return tableRepository.save(tableData);
        }
        return null;  // ถ้าไม่มีโต๊ะให้แสดงว่าไม่พบ
    }

    // ลบโต๊ะจากระบบ
    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }

    public TableModel reserveTable(TableModel table) {
        // อัปเดตข้อมูลในฐานข้อมูล
        return tableRepository.save(table);
    }
    
    public List<TableModel> getAvailableTables() {
        return tableRepository.findAvailableTables();
    }

    public List<TableModel> getAllTablesWithReservations() {
        return tableRepository.findAllWithReservations();
    }
    
}
