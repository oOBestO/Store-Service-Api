package com.example.Store_Service.Api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuName;
    private double price;
    private String category;

    private String imageUrl; // ✅ เปลี่ยนจาก BYTEA เป็น URL ของรูป
}