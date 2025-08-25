package Controller;

import cab.demo.user;
import org.apache.catalina.User;
import service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService a){ this.authService = a; }

    @GetMapping("/login") public String loginPage(){ return "auth-login"; }
    @GetMapping("/register") public String registerPage(){ return "auth-register"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String password,
                          HttpSession session, Model model){
        var user = authService.login(email, password);
        if (user == null){ model.addAttribute("error", "Invalid credentials"); return "auth-login"; }
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @PostMapping("/register")
    public String doRegister(user u, Model model){
        var created = authService.register((user) u);
        if (created == null){
            model.addAttribute("error", "Email already registered");
            return "auth-register";
        }
        model.addAttribute("msg", "Registered! Please login.");
        return "auth-login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}