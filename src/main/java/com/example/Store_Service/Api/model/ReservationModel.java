    package com.example.Store_Service.Api.model;

    import java.time.LocalTime;
    
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

        @Column(name = "start_time")
        private LocalTime startTime;

        @Column(name = "end_time")
        private LocalTime endTime;

    
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
