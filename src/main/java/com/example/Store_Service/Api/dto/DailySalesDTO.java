package com.example.Store_Service.Api.dto;

public class DailySalesDTO {
    private String date;
    private double totalAmount;

    public DailySalesDTO() {
    }

    public DailySalesDTO(String date, double totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}