package com.example.cabbooking.service;

import com.example.cabbooking.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUserId(Long userId);
    List<User> getAllUsers();
    Optional<User> updateUser(Long id, User user);
    void deleteUser(Long id);
    Optional<User> findByEmail(String email);
}

