package com.rentmycar.service;

import com.rentmycar.enums.CarType;
import com.rentmycar.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICarService {
    List<Car> findAll();
    ResponseEntity<Car> find(Long id);
    ResponseEntity<List<Car>> findByName(String name);
    ResponseEntity<List<Car>> findByCarType(CarType type);
    Car create(Car car);
    ResponseEntity<Car> update(Car newCar, Long id);
    ResponseEntity<HttpStatus> delete(Long id);
}
