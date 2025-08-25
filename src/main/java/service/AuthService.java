package service;

import cab.demo.user;
import repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepo;
    public AuthService(UserRepository userRepo){ this.userRepo = userRepo; }

    public user login(String email, String password){
        return userRepo.findByEmail(email, password);

    }

    public user register(user u){
        if (userRepo.findByEmail(u.getEmail()).isPresent())
            return null;
        u.setRole("USER");
        return userRepo.save(u);
    }
}