package avans.avd.rmc_v2.model;

import avans.avd.rmc_v2.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private double price;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private LocalDateTime paidAt;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Reservation(User user, ReservationStatus status) {
        this.user = user;
        this.status = status;
    }
}
