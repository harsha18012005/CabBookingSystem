
package com.example.cabbooking.service;

import com.example.cabbooking.entity.Driver;

import java.util.List;

import java.util.Optional;

public interface DriverService {
    Driver createDriver(Driver driver);
    Optional<Driver> getDriverById(Long id);
    Optional<Driver> getDriverByDriverId(Long driverId);
    List<Driver> getAllDrivers();
    Optional<Driver> updateDriver(Long id, Driver driver);
    void deleteDriver(Long id);
    Optional<Driver> findByEmail(String email);
}

