package com.example.cabbooking.service;

import com.example.cabbooking.entity.Admin;
import com.example.cabbooking.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repo;

    public AdminServiceImpl(AdminRepository repo) {
        this.repo = repo;
    }
    @Override
    public Admin findByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }
    @Override
    public Admin createAdmin(Admin admin) {
        return repo.save(admin);
    }
    public boolean validateLogin(String username, String password) {
        Optional<Admin> adminOpt = repo.findByUsername(username);
        return adminOpt.filter(a -> a.getPassword().equals(password)).isPresent();
    }
}


