package com.example.cabbooking.controller;

import com.example.cabbooking.entity.Admin;
import com.example.cabbooking.repository.AdminRepository;
import com.example.cabbooking.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;
    private final AdminRepository adminRepo;

    public AdminController(AdminService adminService, AdminRepository adminRepo) {
        this.adminService = adminService;
        this.adminRepo = adminRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        Optional<Admin> existing = adminRepo.findByEmail(admin.getEmail()); // ✅ now works
        if(existing.isPresent() && existing.get().getPassword().equals(admin.getPassword())) {
            return ResponseEntity.ok("SUCCESS");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}



