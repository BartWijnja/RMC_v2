package avans.avd.rmc_v2.dto;

import avans.avd.rmc_v2.enums.CarType;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CarDto {
    private Long id;
    private String brand;
    private String brandType;
    private String model;
    private String licensePlateNumber;
    private Double consumption;
    private int costPrice;
    private CarType carType;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
