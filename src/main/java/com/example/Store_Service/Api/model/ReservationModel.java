    package com.example.Store_Service.Api.model;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    
    import jakarta.persistence.*;
    import lombok.Data;

    @Data
    @Entity
    @Table(name = "reservation")
    public class ReservationModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "table_id", nullable = false)
        @JsonBackReference // ✅ ป้องกันการวนซ้ำ
        private TableModel table;

        @Column(name = "customer_name")
        private String customerName;

        @Column(name = "phone_number")
        private String phoneNumber;

        @Column(name = "reservation_time")
        private String reservationTime;
    
        // ✅ เพิ่ม index จาก TableModel ด้วย @Transient
    @Transient
    private String tableIndex;

    // ✅ Getter สำหรับดึง index จาก TableModel
    public String getTableIndex() {
        if (table != null) {
            return table.getIndex();
        }
        return null;
    }
    }
