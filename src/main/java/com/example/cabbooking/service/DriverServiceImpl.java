
package com.example.cabbooking.service;

import com.example.cabbooking.entity.Driver;
import com.example.cabbooking.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repo;

    public DriverServiceImpl(DriverRepository repo) { this.repo = repo; }

    @Override
    public Driver createDriver(Driver driver) {
        return repo.save(driver);
    }


    @Override
    public Optional<Driver> getDriverById(Long id) { return repo.findById(id); }

    @Override
    public Optional<Driver> getDriverByDriverId(Long driverId) { return repo.findByDriverId(driverId); }

    @Override
    public List<Driver> getAllDrivers() { return repo.findAll(); }

    @Override
    public Optional<Driver> updateDriver(Long id, Driver driver) {
        return repo.findById(id).map(existing -> {
            existing.setFullName(driver.getFullName());
            existing.setEmail(driver.getEmail());
            existing.setPassword(driver.getPassword());
            existing.setPhone(driver.getPhone());
            existing.setVehicleModel(driver.getVehicleModel());
            existing.setVehicleNumber(driver.getVehicleNumber());
            existing.setAadhaarNumber(driver.getAadhaarNumber());
            existing.setLicenseNumber(driver.getLicenseNumber());
            return repo.save(existing);
        });
    }

    @Override
    public void deleteDriver(Long id) { repo.deleteById(id); }

    @Override
    public Optional<Driver> findByEmail(String email) { return repo.findByEmail(email); }
}


