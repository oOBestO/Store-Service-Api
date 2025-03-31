package com.example.Store_Service.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Store_Service.Api.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    Orders findTopByPaidFalseOrderByCreatedAtDesc();
    List<Orders> findByPaidFalseOrderByCreatedAtAsc(); // ✅
    List<Orders> findByPaidTrue();
}