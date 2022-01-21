package com.rentmycar.controller;

import com.rentmycar.enums.LocationType;
import com.rentmycar.model.Location;
import com.rentmycar.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {
    @Autowired
    private LocationService service;

    @GetMapping("")
    public List<Location> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Location> find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Location>> findByType(@PathVariable LocationType type) {
        return service.findByType(type);
    }

    @PostMapping("")
    public Location create(@RequestBody Location location) {
        return service.create(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> update(@RequestBody Location newLocation, @PathVariable Long id) {
        return service.update(newLocation, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) { return service.delete(id); }
}
