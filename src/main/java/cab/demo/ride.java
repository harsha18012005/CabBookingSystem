package cab.demo;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity @Table(name="rides")
@Data @NoArgsConstructor @AllArgsConstructor
public class ride {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name="user_id")
    private user user;

    @ManyToOne @JoinColumn(name="cab_id")
    private cab cab;

    @ManyToOne @JoinColumn(name="driver_id")
    private driver driver;

    private String pickup;
    private String dropoff;
    private String city;

    private BigDecimal fare;

    private String status;

    @Column(name="created_at")
    private Instant createdAt = Instant.now();

    public HttpServletResponse getCab() {
        return (HttpServletResponse) cab;

    }
}
