package avans.avd.rmc_v2.model;


import avans.avd.rmc_v2.enums.UserRole;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private String phoneNumber;
    private String iban;
    @NotNull
    private String email;
    @NotNull
    private String password;
    // join column with Car
    @OneToMany(mappedBy= "user",
        orphanRemoval = true)
    private List<Car> car;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

