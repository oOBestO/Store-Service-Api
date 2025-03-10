package com.example.Store_Service.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Store_Service.Api.model.TableModel;

@Repository
public interface TableRepository extends JpaRepository<TableModel, Long> {  // ใช้ Long แทน String
}
