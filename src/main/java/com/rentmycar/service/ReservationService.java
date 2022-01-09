package com.rentmycar.service;

import com.rentmycar.model.Reservation;
import com.rentmycar.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private ReservationRepository repository;

    public List<Reservation> findAll() { return repository.findAll(); }

    public ResponseEntity<Reservation> find(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.findById(id).get());
    }

    public ResponseEntity<List<Reservation>> findByReservation(Long userId, Long carDisplayId) {
        List<Reservation> allReservations = repository.findAll();
        List<Reservation> matchingReservations = new ArrayList<>();

        for (Reservation reservation : allReservations) {
            if (reservation.getCarDisplay().getId().equals(carDisplayId) && reservation.getUser().getId().equals(userId)) {
                matchingReservations.add(reservation);
            }
        }

        return ResponseEntity.ok(matchingReservations);
    }

    public Reservation create(Reservation reservation) {
        reservation.setCreatedAt(LocalDateTime.now());
        return repository.save(reservation);
    }

    public ResponseEntity<Reservation> update(Reservation newReservation, Long id) {
        Optional<Reservation> optionalReservation = Optional.ofNullable(repository.findById(id).orElseThrow(
                () -> new IllegalStateException("reservation with id " + id + " does not exist")
        ));

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setTotalPrice(newReservation.getTotalPrice());
//            reservation.setStatus(newReservation.getStatus());
            reservation.setDaysReserved(newReservation.getDaysReserved());
            reservation.setUser(newReservation.getUser());
            reservation.setCarDisplay(newReservation.getCarDisplay());

            return ResponseEntity.ok(repository.save(reservation));
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<HttpStatus> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
