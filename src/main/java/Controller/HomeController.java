package Controller;

import service.RideService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private final RideService rideService;
    public HomeController(RideService r){ this.rideService = r; }

    @GetMapping("/")
    public String index(){ return "index"; }

    @GetMapping("/search")
    public String searchPage(){ return "search"; }

    @GetMapping("/available")
    public String available(@RequestParam String city, Model model){
        model.addAttribute("cabs", rideService.searchAvailableCabs(city));
        model.addAttribute("city", city);
        return "search";
    }
}
