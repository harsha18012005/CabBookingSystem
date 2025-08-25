package Controller;

import cab.demo.cab;
import cab.demo.driver;
import cab.demo.user;
import service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService a){ this.adminService = a; }

    private boolean isAdmin(HttpSession session){
        var u = (User) session.getAttribute("user");
        return u != null && "ADMIN".equals(u.getRoles());
    }

    @GetMapping("/drivers")
    public String drivers(Model model, HttpSession session){
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("drivers", adminService.allDrivers());
        return "admin-drivers";
    }

    @PostMapping("/drivers")
    public String addDriver(driver d, HttpSession session){
        if (!isAdmin(session)) return "redirect:/login";
        adminService.addDriver(d);
        return "redirect:/admin/drivers";
    }

    @GetMapping("/cabs")
    public String cabs(Model model, HttpSession session){
        if (!isAdmin(session)) return "redirect:/login";
        model.addAttribute("cabs", adminService.allCabs());
        return "admin-cabs";
    }

    @PostMapping("/cabs")
    public String addCab(cab c, HttpSession session){
        if (!isAdmin(session)) return "redirect:/login";
        if (c.getStatus()==null) c.setStatus("AVAILABLE");
        adminService.addCab(c);
        return "redirect:/admin/cabs";
    }
}
