package avans.avd.rmc_v2.model;

import avans.avd.rmc_v2.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    private String reservationNumber;
    @ManyToOne
    private User user;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private LocalDateTime paidAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Reservation(String reservationNumber, User user, ReservationStatus status) {
        this.reservationNumber = reservationNumber;
        this.user = user;
        this.status = status;
    }
}
