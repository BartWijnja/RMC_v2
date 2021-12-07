package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.model.Car;
import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarService(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {

        String licensePlateNumber = car.getLicensePlateNumber();
        Optional<Car> carOptional = carRepository.findCarByLicensePlateNumber(licensePlateNumber);

        if (carOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "license plate number: " + licensePlateNumber + " already exists.");
        }

        return carRepository.save(car);

    }

    public ResponseEntity<Car> updateCar(Car newCar, Long id) {
        Optional<Car> optionalCar = Optional.ofNullable(carRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("car with id " + id + " does not exist")
        ));

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setBrand(newCar.getBrand());
            car.setBrandType(newCar.getBrandType());
            car.setModel(newCar.getModel());
            car.setLicensePlateNumber(newCar.getLicensePlateNumber());
            car.setConsumption(newCar.getConsumption());
            car.setCostPrice(newCar.getCostPrice());
            car.setCarType(newCar.getCarType());

            return ResponseEntity.ok(carRepository.save(car));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<HttpStatus> deleteCar(Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }

//    public List<Car> getCarsByUser(User user) {
//        return carRepository.findAllByUser(user)
//                .stream()
//                .map(obj -> modelMapper.map(obj, Car.class))
//                .collect(Collectors.toList());
//    }

//    public Car findCarByUser(Long id, User user) {
//        Optional<Car> carOptional = carRepository.findById(id);
//        if(carOptional.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found.");
//        }
//        Car car = carOptional.get();
//
//        if (car.getUser() != user) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Car does not belong to user.");
//        }
//        return car;
//    }


}
