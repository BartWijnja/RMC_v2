package avans.avd.rmc_v2.model;

import avans.avd.rmc_v2.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String brandType;
    private String model;
    private String licensePlateNumber;
    private Double consumption; // per 100km
    private int costPrice;
    private int tco;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;

}
