package com.example.cabbooking.service;

import com.example.cabbooking.entity.Admin;



public interface AdminService {
    Admin findByUsername(String username);

    Admin createAdmin(Admin admin);
    boolean validateLogin(String username, String password);
}




