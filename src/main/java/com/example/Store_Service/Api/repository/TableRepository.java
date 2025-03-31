package com.example.Store_Service.Api.repository;

import com.example.Store_Service.Api.model.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableModel, Long> {

    @Query("SELECT DISTINCT t FROM TableModel t LEFT JOIN t.reservations")
    List<TableModel> findAllTables();

    @Query("SELECT t FROM TableModel t LEFT JOIN t.reservations r WHERE r IS NULL")
    List<TableModel> findAvailableTables();

    @Query("SELECT DISTINCT t FROM TableModel t LEFT JOIN t.reservations")
    List<TableModel> findAllWithReservations();
    
    List<TableModel> findByIdNotIn(List<Long> ids);
    boolean existsByIndex(String index);
}
