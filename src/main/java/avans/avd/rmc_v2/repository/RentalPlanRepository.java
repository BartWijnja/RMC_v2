package avans.avd.rmc_v2.repository;

import avans.avd.rmc_v2.model.RentalPlan;
import avans.avd.rmc_v2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalPlanRepository extends JpaRepository<RentalPlan, Long> {
    List<RentalPlan> findAllByUser(Long id);

}
