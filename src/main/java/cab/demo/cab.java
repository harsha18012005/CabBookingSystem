package cab.demo;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="cabs")
@Data @NoArgsConstructor @AllArgsConstructor
public class cab {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="registration_no", unique = true)
    private String registrationNo;

    private String model;
    private String city;
    private String status; // AVAILABLE, ON_TRIP, INACTIVE

    @ManyToOne
    @JoinColumn(name="driver_id")
    private driver driver;
}
