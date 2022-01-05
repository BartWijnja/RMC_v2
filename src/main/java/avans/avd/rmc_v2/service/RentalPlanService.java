package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.model.Car;
import avans.avd.rmc_v2.model.RentalPlan;
import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.repository.CarRepository;
import avans.avd.rmc_v2.repository.RentalPlanRepository;
import avans.avd.rmc_v2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RentalPlanService {
    private final RentalPlanRepository rentalPlanRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public RentalPlanService(RentalPlanRepository rentalPlanRepository, CarRepository carRepository, UserRepository userRepository) {
        this.rentalPlanRepository = rentalPlanRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<RentalPlan> getAllRentalPlans() {
        return rentalPlanRepository.findAll();
    }

    public RentalPlan createRentalPlan(RentalPlan rentalPlan) {
        Optional<Car> carOptional = carRepository.findById(rentalPlan.getCar().getId());

        if (carOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car does not exist.");
        }

        Car car = carOptional.get();
        Optional<RentalPlan> rentalPlanOptional = rentalPlanRepository.findAllByCar(car);

        if (rentalPlanOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This car already has a rental plan.");
        }

        return rentalPlanRepository.save(rentalPlan);
    }

    public ResponseEntity<HttpStatus> deleteRentalPlan(Long id) {

        if (!rentalPlanRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            rentalPlanRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
}