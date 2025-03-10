package com.example.Store_Service.Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Store_Service.Api.model.TableModel;
import com.example.Store_Service.Api.service.TableService;

@RestController
@RequestMapping("/api/tables")
@CrossOrigin(origins = "http://localhost:4200") // เพิ่มแค่โดเมนที่อนุญาตให้เชื่อมต่อ
public class TableController {

    @Autowired
    private TableService tableService;

    @PostMapping
    public TableModel createTable(@RequestBody TableModel table) {
        return tableService.saveTable(table);
    }
}
