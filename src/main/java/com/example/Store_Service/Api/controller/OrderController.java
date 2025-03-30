package com.example.Store_Service.Api.controller;

import org.springframework.web.bind.annotation.*;

import com.example.Store_Service.Api.dto.DailySalesDTO;
import com.example.Store_Service.Api.dto.OrderDTO;
import com.example.Store_Service.Api.dto.OrderRequest;
import com.example.Store_Service.Api.model.Orders;
import com.example.Store_Service.Api.repository.OrderRepository;
import com.example.Store_Service.Api.service.OrderService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveOrder(@RequestBody OrderRequest request) {
        Orders savedOrder = orderService.saveOrder(request);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order saved successfully");
        response.put("orderId", savedOrder.getId());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Map<String, String>> updateStatus(@PathVariable Long orderId, @RequestParam boolean paid) {
    orderService.updatePaymentStatus(orderId, paid);

    Map<String, String> response = new HashMap<>();
    response.put("message", "อัปเดตสถานะสำเร็จ");
    return ResponseEntity.ok(response);
}

    @GetMapping("/all-unpaid")
    public ResponseEntity<List<OrderDTO>> getAllUnpaidOrders() {
        List<OrderDTO> orders = orderService.findAllUnpaidOrderDTOs();
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/daily-sales")
    public ResponseEntity<List<DailySalesDTO>> getDailySales() {
        List<DailySalesDTO> sales = orderService.getDailySales();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{orderId}/status")
    public ResponseEntity<Map<String, Object>> checkPaid(@PathVariable Long orderId) {
        boolean paid = orderService.isPaid(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", paid ? "paid" : "pending");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/paid")
    public ResponseEntity<List<OrderDTO>> getPaidOrders() {
        List<OrderDTO> orders = orderService.findAllPaidOrderDTOs();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Long orderId) {
        Orders order = orderRepository.findTopByPaidFalseOrderByCreatedAtDesc();
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/latest-unpaid")
    public ResponseEntity<OrderDTO> getLatestUnpaidOrder() {
        OrderDTO orderDTO = orderService.findLatestUnpaidOrderDTO();
        if (orderDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDTO);
    }
}