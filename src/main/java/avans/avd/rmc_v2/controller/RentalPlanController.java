package avans.avd.rmc_v2.controller;

import avans.avd.rmc_v2.model.RentalPlan;
import avans.avd.rmc_v2.service.RentalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/rentalplan/")
public class RentalPlanController {
    private final RentalPlanService rentalPlanService;

    @Autowired

    public RentalPlanController(RentalPlanService rentalPlanService) {
        this.rentalPlanService = rentalPlanService;
    }

    @GetMapping("/")
    public List<RentalPlan> getAllRentalPlans() {
        return rentalPlanService.getAllRentalPlans();
    }

}
