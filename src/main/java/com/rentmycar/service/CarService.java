package com.rentmycar.service;

import com.rentmycar.enums.CarType;
import com.rentmycar.model.Car;
import com.rentmycar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ICarService {
    @Autowired
    private CarRepository repository;

    public List<Car> findAll() { return repository.findAll(); }

    public ResponseEntity<Car> find(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(repository.findById(id).get());
    }

    public ResponseEntity<List<Car>> findByName(String name) {
        List<Car> allCars = repository.findAll();
        List<Car> matchingCars = new ArrayList<>();

        for (Car car : allCars) {
            String carName = car.getBrand() + " " + car.getBrandType() + " " + car.getModel();
            if (carName.toLowerCase().contains(name.toLowerCase())) {
                matchingCars.add(car);
            }
        }

        return ResponseEntity.ok(matchingCars);
    }

    public ResponseEntity<List<Car>> findByCarType(CarType type) {
        List<Car> allCars = repository.findAll();
        List<Car> matchingCars = new ArrayList<>();

        for (Car car : allCars) {
            if (car.getCarType() == type) {
                matchingCars.add(car);
            }
        }

        return ResponseEntity.ok(matchingCars);
    }

    public Car create(Car car) {
        car.setCreatedAt(LocalDateTime.now());
        return repository.save(car);
    }

    public ResponseEntity<Car> update(Car newCar, Long id) {
        Optional<Car> optionalCar = Optional.ofNullable(repository.findById(id).orElseThrow(
                () -> new IllegalStateException("car with id " + id + " does not exist")
        ));

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setBrand(newCar.getBrand());
            car.setBrandType(newCar.getBrandType());
            car.setModel(newCar.getModel());
            car.setLicensePlateNumber(newCar.getLicensePlateNumber());
            car.setConsumption(newCar.getConsumption());
            car.setPrice(newCar.getPrice());
            car.setPersonSpace(newCar.getPersonSpace());
            car.setCarType(newCar.getCarType());

            return ResponseEntity.ok(repository.save(car));
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
