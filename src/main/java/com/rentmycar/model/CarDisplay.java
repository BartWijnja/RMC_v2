package com.rentmycar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_displays")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDisplay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total")
    private int total;

    @Column(name = "available")
    private int available;

    @OneToOne
    @JoinColumn(nullable = false, name = "location_id")
    private Location location;

    @OneToOne
    @JoinColumn(nullable = false, name = "car_id")
    private Car car;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
