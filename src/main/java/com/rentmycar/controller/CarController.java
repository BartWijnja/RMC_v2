package com.rentmycar.controller;

import com.rentmycar.enums.CarType;
import com.rentmycar.model.Car;
import com.rentmycar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {
    @Autowired
    private CarService service;

    @GetMapping("")
    public List<Car> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Car> find(@PathVariable Long id) {
        return service.find(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Car>> findByName(@PathVariable String name) { return service.findByName(name); }

    @GetMapping("/type/{carType}")
    public ResponseEntity<List<Car>> findByCarType(@PathVariable CarType carType) { return service.findByCarType(carType); }

    @PostMapping("")
    public Car create(@RequestBody Car car) {
        return service.create(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@RequestBody Car newCar, @PathVariable Long id) {
        return service.update(newCar, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) { return service.delete(id); }
}
