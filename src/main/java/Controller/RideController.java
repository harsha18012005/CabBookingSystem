package Controller;

import cab.demo.riderequest;
import cab.demo.user;
import service.RideService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rides")
public class RideController {
    private final RideService rideService;
    public RideController(RideService r){ this.rideService = r; }

    @GetMapping("/new")
    public String newRidePage(){ return "ride-new"; }

    @PostMapping
    public String createRide(@Valid riderequest req, HttpSession session, Model model){
        var user = (user) session.getAttribute("user");
        if (user == null){ return "redirect:/login"; }
        var ride = rideService.createRide(user, req);
        model.addAttribute("ride", ride);
        return "redirect:/rides/my";
    }

    @GetMapping("/my")
    public String myRides(HttpSession session, Model model){
        var user = (user) session.getAttribute("user");
        if (user == null){ return "redirect:/login"; }
        model.addAttribute("rides", rideService.myRides(user.getId()));
        return "ride-list";
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status){
        rideService.equals(id, status);
        return "redirect:/rides/my";
    }
}
