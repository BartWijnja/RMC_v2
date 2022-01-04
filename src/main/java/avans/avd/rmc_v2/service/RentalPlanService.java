package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.model.Car;
import avans.avd.rmc_v2.model.RentalPlan;
import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.repository.CarRepository;
import avans.avd.rmc_v2.repository.RentalPlanRepository;
import avans.avd.rmc_v2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}