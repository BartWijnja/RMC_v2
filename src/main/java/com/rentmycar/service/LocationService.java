package com.rentmycar.service;

import com.rentmycar.enums.LocationType;
import com.rentmycar.model.Location;
import com.rentmycar.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements ILocationService {
    @Autowired
    private LocationRepository repository;

    public List<Location> findAll() {
        return repository.findAll();
    }

    public ResponseEntity<Location> find(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.findById(id).get());
    }

    public ResponseEntity<List<Location>> findByType(LocationType type) {
        List<Location> allLocations = repository.findAll();
        List<Location> locationsByType = new ArrayList<>();

        for (Location location : allLocations) {
            if (location.getLocationType() == type) {
                locationsByType.add(location);
            }
        }

        return ResponseEntity.ok(locationsByType);
    }

    public Location create(Location location) {
        location.setCreatedAt(LocalDateTime.now());
        return repository.save(location);
    }

    public ResponseEntity<Location> update(Location newLocation, Long id) {
        Optional<Location> optionalLocation = Optional.ofNullable(repository.findById(id).orElseThrow(
                () -> new IllegalStateException("location with id " + id + " does not exist")
        ));

        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            location.setStreet(newLocation.getStreet());
            location.setHouseNumber(newLocation.getHouseNumber());
            location.setPostalCode(newLocation.getPostalCode());
            location.setCity(newLocation.getCity());
            location.setCountry(newLocation.getCountry());
            location.setLocationType(newLocation.getLocationType());

            return ResponseEntity.ok(repository.save(location));
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
