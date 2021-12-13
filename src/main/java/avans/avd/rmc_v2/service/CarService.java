package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.enums.CarType;
import avans.avd.rmc_v2.model.Car;
import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ICarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
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
// todo: fix CalculateCarTco

//    public Car calculateCarTco(Long id, User user, int kilometers) {
//        Car car = findCarByUser(id, user);
//
//        // Cars depreciate at an average of 20% year. This method uses that average.
//        Double depreciation = car.getCostPrice() * 0.2;
//
//        // costPerUnit are fixed prices.
//        CarType carType = car.getCarType();
//        double costPerUnit = 0.00;
//        switch (carType) {
//            case BEV:
//                costPerUnit = 0.2; // €/kWh
//                break;
//            case ICE:
//                costPerUnit = 1.75; // €/l
//                break;
//            case FCEV:
//                costPerUnit = 2.03; // €/kg
//                break;
//        }
//
//        // Calculates yearly fuel cost based on costPerUnit of carType and consumption and provided kms in request
//        Double yearlyFuelCost = costPerUnit * car.getConsumption() * (kilometers / 100);
//        Double totalCostOwnership = depreciation + yearlyFuelCost;
//        Double costPerKilometer = (car.getConsumption() / 100) * costPerUnit;
//
//        return new TcoDto (
//                car.getId(),
//                car.getConsumption(),
//                kilometers,
//                car.getCostPrice(),
//                depreciation,
//                yearlyFuelCost,
//                totalCostOwnership,
//                costPerKilometer
//        );
//    }

}
