package com.example.cabbooking.service;

import com.example.cabbooking.entity.Admin;
import com.example.cabbooking.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repo;

    public AdminServiceImpl(AdminRepository repo) {
        this.repo = repo;
    }

    @Override
    public Admin findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return repo.save(admin);
    }
    public boolean validateLogin(String email, String password) {
        return repo.findByEmail(email)
                .filter(a -> a.getPassword().equals(password))
                .isPresent();
    }

}


