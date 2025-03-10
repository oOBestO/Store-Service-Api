package com.example.Store_Service.Api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"table\"")  // ใช้เครื่องหมาย double quotes เพื่อหลีกเลี่ยงการใช้คำสงวน
public class TableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // ✅ แก้ไขให้ถูกต้อง
    private Long id;

    @Column(name = "index")
    private String index;

    @Column(name = "seats")
    private String seats;

    // Getters and Setters
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
