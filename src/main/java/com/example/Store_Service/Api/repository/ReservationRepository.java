    package com.example.Store_Service.Api.repository;

    import com.example.Store_Service.Api.model.ReservationModel;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {

        @Query("SELECT DISTINCT r FROM ReservationModel r JOIN r.table t WHERE t.id = :tableId")
        List<ReservationModel> findByTableId(Long tableId);
        @Query("SELECT r FROM ReservationModel r JOIN FETCH r.table t") // ✅ JOIN FETCH เพื่อดึงข้อมูลโต๊ะที่เชื่อมกัน
        List<ReservationModel> findAllWithTableIndex();

    }
