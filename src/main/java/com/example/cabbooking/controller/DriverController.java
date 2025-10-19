package com.example.cabbooking.controller;

import com.example.cabbooking.entity.Driver;
import com.example.cabbooking.service.DriverService;
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
        Driver saved = driverService.createDriver(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<?> getDriver(@PathVariable Long driverId) {
        Optional<Driver> driverOpt = driverService.getDriverByDriverId(driverId);
        return driverOpt
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        Optional<Driver> existing = driverService.getDriverById(id);
        if (existing.isPresent()) {
            driverService.updateDriver(id, driver);
            return ResponseEntity.ok(driver);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
}



