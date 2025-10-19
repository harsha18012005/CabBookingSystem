package com.example.cabbooking.service;

import com.example.cabbooking.entity.Admin;



public interface AdminService {
    Admin findByEmail(String email);

    Admin createAdmin(Admin admin);
}




