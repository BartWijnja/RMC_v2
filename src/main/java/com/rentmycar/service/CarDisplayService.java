package com.rentmycar.service;

import com.rentmycar.model.CarDisplay;
import com.rentmycar.repository.CarDisplayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarDisplayService implements ICarDisplayService {
    @Autowired
    private CarDisplayRepository repository;

    public List<CarDisplay> findAll() { return repository.findAll(); }

    public ResponseEntity<CarDisplay> find(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.findById(id).get());
    }

    public ResponseEntity<List<CarDisplay>> findByDisplay(Long locationId, Long carId) {
        List<CarDisplay> allCarDisplays = repository.findAll();
        List<CarDisplay> matchingCarDisplays = new ArrayList<>();

        for (CarDisplay carDisplay : allCarDisplays) {
            if (carDisplay.getLocation().getId().equals(locationId) && carDisplay.getCar().getId().equals(carId)) {
                matchingCarDisplays.add(carDisplay);
            }
        }

        return ResponseEntity.ok(matchingCarDisplays);
    }

    public CarDisplay create(CarDisplay carDisplay) {
        carDisplay.setCreatedAt(LocalDateTime.now());
        return repository.save(carDisplay);
    }

    public ResponseEntity<CarDisplay> update(CarDisplay newCarDisplay, Long id) {
        Optional<CarDisplay> optionalCarDisplay = Optional.ofNullable(repository.findById(id).orElseThrow(
                () -> new IllegalStateException("carDisplay with id " + id + " does not exist")
        ));

        if (optionalCarDisplay.isPresent()) {
            CarDisplay carDisplay = optionalCarDisplay.get();
            carDisplay.setTotal(newCarDisplay.getTotal());
            carDisplay.setAvailable(newCarDisplay.getAvailable());
            carDisplay.setLocation(newCarDisplay.getLocation());
            carDisplay.setCar(newCarDisplay.getCar());

            return ResponseEntity.ok(repository.save(carDisplay));
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
