package com.rentmycar.controller;

import com.rentmycar.model.Reservation;
import com.rentmycar.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {
    @Autowired
    private ReservationService service;

    @GetMapping("")
    public List<Reservation> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping("/{userId}/reservation/{carDisplayId}")
    public ResponseEntity<List<Reservation>> findByReservation(@PathVariable(name = "userId") Long userId, @PathVariable(name = "carDisplayId") Long carDisplayId) {
        return service.findByReservation(userId, carDisplayId);
    }

    @PostMapping("")
    public Reservation create(@RequestBody Reservation reservation) {
        return service.create(reservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> update(@RequestBody Reservation newReservation, @PathVariable Long id) {
        return service.update(newReservation, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) { return service.delete(id); }
}
