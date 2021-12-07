package avans.avd.rmc_v2.controller;



import avans.avd.rmc_v2.model.Car;
import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.service.CarService;
import avans.avd.rmc_v2.service.UserService;
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
    private UserService userService;

    @Autowired
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping("/getcars")
    public List<Car> getCarList() {
        return carService.getAllCars();
    }

    @PostMapping("/createcar")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {

        return ResponseEntity.accepted().body(carService.createCar(car));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Car> updateCar(@RequestBody Car newCar, @PathVariable Long id) {
        return carService.updateCar(newCar, id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {
        return carService.deleteCar(id);
    }

//    @GetMapping("/getcarsbyuser")
//    public List<Car> getCarsByUser(Long id) {
//        User user = userService.getUserById(id);
//        return carService.getCarsByUser(user);
//    }

    @GetMapping("/getcarsbyuser/{id}")
    public Car getCarByUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return carService.findCarByUser(id, user);
    }
}


