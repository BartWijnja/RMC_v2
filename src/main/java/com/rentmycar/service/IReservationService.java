package com.rentmycar.service;

import com.rentmycar.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IReservationService {
    List<Reservation> findAll();
    ResponseEntity<Reservation> find(Long id);
    ResponseEntity<List<Reservation>> findByReservation(Long userId, Long carDisplayId);
    Reservation create(Reservation reservation);
    ResponseEntity<Reservation> update(Reservation newReservation, Long id);
    ResponseEntity<HttpStatus> delete(Long id);
}
