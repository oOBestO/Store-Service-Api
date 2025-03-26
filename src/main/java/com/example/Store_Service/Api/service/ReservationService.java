package com.example.Store_Service.Api.service;

import com.example.Store_Service.Api.model.ReservationModel;
import com.example.Store_Service.Api.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationModel saveReservation(ReservationModel reservation) {
        return reservationRepository.save(reservation);
    }

    public List<ReservationModel> getReservationsByTableId(Long tableId) {
        return reservationRepository.findByTableId(tableId);
    }

    public List<ReservationModel> getAllReservationsWithIndex() {
        return reservationRepository.findAllWithTableIndex(); // ✅ เรียกใช้ query จาก repository
    }

    // ✅ ลบการจองตาม ID
    public void deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Reservation with ID " + id + " not found.");
        }
    }
}
    