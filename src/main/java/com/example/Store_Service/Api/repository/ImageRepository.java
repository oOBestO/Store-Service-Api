package com.example.Store_Service.Api.repository;

import com.example.Store_Service.Api.model.TableImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<TableImage, Long> {

    // ✅ ใช้เมธอดเดียวที่รับค่าเป็น int
    Optional<TableImage> findByIndex(int index);
}
