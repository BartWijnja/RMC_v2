package avans.avd.rmc_v2.repository;//package avans.informatica.avdinformaticarentmycar.repository;


import avans.avd.rmc_v2.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Haalt de queries op
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByLicensePlateNumber(String licensePlateNumber);
}
