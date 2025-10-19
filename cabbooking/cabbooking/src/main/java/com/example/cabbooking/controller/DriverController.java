
package com.example.cabbooking.controller;

import com.example.cabbooking.entity.Driver;
import com.example.cabbooking.entity.User;
import com.example.cabbooking.service.DriverService;
import com.example.cabbooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin("*")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<?> createDriver(@RequestBody Driver driver) {
        if (driverService.findByEmail(driver.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.createDriver(driver));
    }


    @GetMapping("/{driverId}")
    public ResponseEntity<?> getDriver(@PathVariable Long driverId) {
        Optional<Driver> driverOpt = driverService.getDriverByDriverId(driverId);
        return driverOpt.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found"));
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<?> updateDriver(@PathVariable Long driverId, @RequestBody Driver driver) {
        Optional<Driver> existing = driverService.getDriverByDriverId(driverId);
        if (existing.isPresent()) {
            driverService.updateDriver(existing.get().getId(), driver);
            return ResponseEntity.ok(driver);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");
    }


    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long driverId) {
        driverService.deleteDriver(driverId);
        return ResponseEntity.noContent().build();
    }
}

