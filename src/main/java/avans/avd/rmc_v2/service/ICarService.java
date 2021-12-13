package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

//Blueprint of CarService
public interface ICarService {
    List<Car> getAllCars();
    Car createCar(Car car);
    ResponseEntity<Car> updateCar(Car newCar, Long id);
    ResponseEntity<HttpStatus> deleteCar(Long id);
}
