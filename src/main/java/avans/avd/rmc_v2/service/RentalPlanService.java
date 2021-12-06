package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.model.RentalPlan;
import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.repository.RentalPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalPlanService {
    private final RentalPlanRepository rentalPlanRepository;

    @Autowired
    public RentalPlanService(RentalPlanRepository rentalPlanRepository) {
        this.rentalPlanRepository = rentalPlanRepository;
    }

    public List<RentalPlan> getRentalPlansByUser(Long id) {
        return rentalPlanRepository.findAllByUser(id);
    }
}
