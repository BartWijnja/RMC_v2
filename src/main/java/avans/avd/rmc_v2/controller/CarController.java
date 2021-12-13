package avans.avd.rmc_v2.controller;



import avans.avd.rmc_v2.model.Car;
import avans.avd.rmc_v2.service.CarService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public List<Car> getCarList() {
        return carService.getAllCars();
    }

    @PostMapping("/")
    public Car createCar(@RequestBody Car car) {

        return carService.createCar(car);
    }

    @PutMapping(path = "/{id}")
    public Car updateCar(@RequestBody Car newCar, @PathVariable Long id) {
        return carService.updateCar(newCar, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        return carService.deleteCar(id);
    }


}


