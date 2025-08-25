package cab.demo;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="drivers")
@Data @NoArgsConstructor @AllArgsConstructor
public class driver {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String city;
    private boolean active = true;
}
