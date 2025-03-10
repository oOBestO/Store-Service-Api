package com.example.Store_Service.Api.service;

import com.example.Store_Service.Api.model.TableModel;
import com.example.Store_Service.Api.repository.TableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public TableModel saveTable(TableModel table) {
        return tableRepository.save(table);
    }
}
