package cab.demo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class riderequest {
    @NotBlank private String city;
    @NotBlank private String pickup;
    @NotBlank private String dropoff;
}
