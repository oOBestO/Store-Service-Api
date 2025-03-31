package com.example.Store_Service.Api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.Store_Service.Api.model.Orders;

public class OrderDTO {
    private Long id;
    private int tableNumber;
    private String customerName;
    private double totalAmount;
    private boolean paid;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> items;
    private int tableIndex;
    private boolean delivered;

  // ✅ Constructor ที่ต้องเพิ่ม
    public OrderDTO(Orders order) {
        this.id = order.getId();
        this.customerName = order.getCustomerName();
        this.tableNumber = order.getTableNumber();
        this.totalAmount = order.getTotalAmount();
        this.paid = order.isPaid(); // ✅ อย่าลืม!
        this.createdAt = order.getCreatedAt(); // ✅ ควรใส่เวลาด้วย
        this.delivered = order.isDelivered(); // ✅ สำคัญ!

        this.items = order.getItems() // << ตรวจตรงนี้ให้ใช้ชื่อ field ที่ถูกต้อง
            .stream()
            .map(OrderItemDTO::new)
            .collect(Collectors.toList());
    }
    // ✅ Getter/Setter
    public boolean isDelivered() {return delivered;}
    public void setDelivered(boolean delivered) {this.delivered = delivered;}

    public int getTableIndex() {return tableIndex;}
    public void setTableIndex(int tableIndex) {this.tableIndex = tableIndex;}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getTableNumber() { return tableNumber; }
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }
}
