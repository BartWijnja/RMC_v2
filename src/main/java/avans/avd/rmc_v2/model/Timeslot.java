package avans.avd.rmc_v2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "timeslots")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime startAt;
    private LocalTime endAt;
}

