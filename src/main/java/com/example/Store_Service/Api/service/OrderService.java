package com.example.Store_Service.Api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Store_Service.Api.dto.DailySalesDTO;
import com.example.Store_Service.Api.dto.OrderDTO;
import com.example.Store_Service.Api.dto.OrderItemDTO;
import com.example.Store_Service.Api.dto.OrderItemRequest;
import com.example.Store_Service.Api.dto.OrderRequest;
import com.example.Store_Service.Api.model.OrderItem;
import com.example.Store_Service.Api.model.Orders;
import com.example.Store_Service.Api.repository.OrderRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Function;
@Service
public class OrderService {
    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Orders saveOrder(OrderRequest request) {
        Orders order = new Orders();
        order.setTableNumber(request.getTableNumber());
        order.setCustomerName(request.getCustomerName());
        order.setTotalAmount(request.getTotalAmount());
        order.setPaid(request.isPaid());

        List<OrderItem> items = request.getItems().stream().map(i -> {
            OrderItem item = new OrderItem();
            item.setMenuName(i.getMenuName());
            item.setQuantity(i.getQuantity());
            item.setPrice(i.getPrice());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        return orderRepo.save(order);
    }

    public void updatePaymentStatus(Long orderId, boolean paid) {
        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("ไม่พบคำสั่งซื้อ"));
        order.setPaid(paid);
        orderRepo.save(order);
    }

    public boolean isPaid(Long orderId) {
        Orders order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("ไม่พบคำสั่งซื้อ"));
        return order.isPaid();
    }

    public Orders getOrderById(Long orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }

    public OrderDTO convertToDTO(Orders order) {
    OrderDTO dto = new OrderDTO(order);
    dto.setId(order.getId());
    dto.setTableNumber(order.getTableNumber());
    dto.setCustomerName(order.getCustomerName());
    dto.setTotalAmount(order.getTotalAmount());
    dto.setPaid(order.isPaid());
    dto.setCreatedAt(order.getCreatedAt());

    List<OrderItemDTO> itemDTOs = order.getItems().stream().map(item ->
        new OrderItemDTO(item.getMenuName(), item.getQuantity(), item.getPrice())
    ).collect(Collectors.toList());

    dto.setItems(itemDTOs);
    return dto;
}

    public OrderDTO findLatestUnpaidOrderDTO() {
        Orders order = orderRepo.findTopByPaidFalseOrderByCreatedAtDesc();
        if (order == null) return null;
        return convertToDTO(order);
    }

    public List<OrderDTO> findAllPaidOrderDTOs() {
        List<Orders> paidOrders = orderRepo.findByPaidTrue();
        return paidOrders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    public List<OrderDTO> findAllUnpaidOrderDTOs() {
        List<Orders> unpaidOrders = orderRepo.findByPaidFalseOrderByCreatedAtAsc();
        return unpaidOrders.stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    public List<DailySalesDTO> getDailySales() {
        return orderRepo.findByPaidTrue().stream()
            .collect(Collectors.groupingBy(
                order -> order.getCreatedAt().toLocalDate().toString(), // "yyyy-MM-dd"
                Collectors.summingDouble(Orders::getTotalAmount)
            ))
            .entrySet().stream()
            .map(e -> new DailySalesDTO(e.getKey(), e.getValue()))
            .sorted(Comparator.comparing((Function<DailySalesDTO, LocalDate>) 
                d -> LocalDate.parse(d.getDate())))
            .collect(Collectors.toList());
    }
    public void updateDeliveryStatus(Long orderId, boolean delivered) {
        Orders order = orderRepo.findById(orderId)
            .orElseThrow(() -> new RuntimeException("ไม่พบคำสั่งซื้อ"));
        order.setDelivered(delivered);
        orderRepo.save(order);
    }
    
}