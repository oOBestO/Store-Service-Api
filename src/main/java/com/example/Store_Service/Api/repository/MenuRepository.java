package com.example.Store_Service.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Store_Service.Api.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    boolean existsByMenuName(String menuName);
    
    List<Menu> findAllByIdIn(List<Long> ids); // ✅ ค้นหาเมนูตาม id[]
}