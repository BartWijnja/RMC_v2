package com.rentmycar.model;

import com.rentmycar.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "brand_type")
    private String brandType;

    @Column(name = "model")
    private String model;

    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    @Column(name = "consumption")
    private double consumption;

    @Column(name = "price")
    private double price;

    @Column(name = "person_space")
    private int personSpace;

    @Column(name = "car_type")
    private CarType carType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
