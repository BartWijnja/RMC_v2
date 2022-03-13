package com.rentmycar.controller;

import com.rentmycar.enums.CarType;
import com.rentmycar.model.CarDisplay;
import com.rentmycar.service.CarDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car-displays")
public class CarDisplayController {
    @Autowired
    private CarDisplayService service;

    @GetMapping("")
    public List<CarDisplay> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<CarDisplay> find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping("/{locationId}/display")
    public ResponseEntity<List<CarDisplay>> findAllByLocation(@PathVariable(name = "locationId") Long locationId) {
        return service.findAllByLocation(locationId);
    }

    @GetMapping("/{locationId}/display/type/{carType}")
    public ResponseEntity<List<CarDisplay>> findByDisplayWithType(
            @PathVariable(name = "locationId") Long locationId,
            @PathVariable(name = "carType") CarType carType
    ) {
        return service.findByDisplayWithType(locationId, carType);
    }

    @GetMapping("/{locationId}/display/{carId}")
    public ResponseEntity<List<CarDisplay>> findByDisplay(@PathVariable(name = "locationId") Long locationId, @PathVariable(name = "carId") Long carId) {
        return service.findByDisplay(locationId, carId);
    }

    @PostMapping("")
    public CarDisplay create(@RequestBody CarDisplay carDisplay) {
        return service.create(carDisplay);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDisplay> update(@RequestBody CarDisplay newCarDisplay, @PathVariable Long id) {
        return service.update(newCarDisplay, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) { return service.delete(id); }
}
