package com.rentmycar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "total_price")
    private double totalPrice;

//    @Column(name = "status")
//    private ReservationStatus status;

    @Column(name = "days_reserved")
    private int daysReserved;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(nullable = false, name = "car_display_id")
    private CarDisplay carDisplay;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
