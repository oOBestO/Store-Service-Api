package com.example.Store_Service.Api.repository;

import com.example.Store_Service.Api.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    // สามารถเพิ่ม method ค้นหาข้อมูลเฉพาะที่ต้องการได้
}
