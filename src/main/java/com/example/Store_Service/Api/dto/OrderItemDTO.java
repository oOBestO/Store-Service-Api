package com.example.Store_Service.Api.dto;

import com.example.Store_Service.Api.model.OrderItem;

public class OrderItemDTO {
    private String menuName;
    private int quantity;
    private double price;

    // ✅ Constructors
    public OrderItemDTO() {}

    public OrderItemDTO(String menuName, int quantity, double price) {
        this.menuName = menuName;
        this.quantity = quantity;
        this.price = price;
    }
  // ✅ Constructor
    public OrderItemDTO(OrderItem item) {
        this.menuName = item.getMenuName(); 
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
    }
    // ✅ Getter/Setter
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
