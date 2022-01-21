package com.rentmycar.service;

import com.rentmycar.enums.LocationType;
import com.rentmycar.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ILocationService {
    List<Location> findAll();
    ResponseEntity<Location> find(Long id);
    ResponseEntity<List<Location>> findByType(LocationType type);
    Location create(Location location);
    ResponseEntity<Location> update(Location newLocation, Long id);
    ResponseEntity<HttpStatus> delete(Long id);
}
