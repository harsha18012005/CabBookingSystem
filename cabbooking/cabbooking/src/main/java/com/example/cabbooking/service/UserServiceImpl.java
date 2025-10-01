
package com.example.cabbooking.service;

import com.example.cabbooking.entity.User;
import com.example.cabbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }
    public User createUser(User user) {
        if(user.getUserId() == null) {
            user.setUserId(System.currentTimeMillis()); // or UUID
        }
        return repo.save(user);
    }


    @Override
    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<User> getUserByUserId(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        return repo.findById(id).map(existing -> {
            existing.setFullName(user.getFullName());
            existing.setEmail(user.getEmail());
            existing.setPassword(user.getPassword());
            return repo.save(existing);
        });
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}
