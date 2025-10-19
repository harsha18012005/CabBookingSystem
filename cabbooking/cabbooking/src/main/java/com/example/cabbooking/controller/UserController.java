
package com.example.cabbooking.controller;

import com.example.cabbooking.entity.User;
import com.example.cabbooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if(userService.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already registered");
        }
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        Optional<User> userOpt = userService.getUserByUserId(userId);
        if(userOpt.isPresent()) return ResponseEntity.ok(userOpt.get());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> existing = userService.getUserByUserId(userId);
        if(existing.isPresent()) {
            userService.updateUser(existing.get().getId(), user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
//        Optional<User> existing = userService.getUserByUserId(userId);
//        if(existing.isPresent()){
//            userService.deleteUser(existing.get().getId());
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//    }
//@DeleteMapping("/{id}")
//public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//    try {
//        userService.deleteUser(id);
//        return ResponseEntity.ok("User deleted successfully");
//    } catch (Exception e) {
//        return ResponseEntity.status(500).body("Error deleting user: " + e.getMessage());
//    }
//}
@DeleteMapping("/users/{id}")
public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.noContent().build();
}

}


