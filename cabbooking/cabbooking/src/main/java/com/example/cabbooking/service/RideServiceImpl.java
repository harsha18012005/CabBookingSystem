//
//
//package com.example.cabbooking.service;
//
//import com.example.cabbooking.dto.RideDTO;
//import com.example.cabbooking.entity.Driver;
//import com.example.cabbooking.entity.Ride;
//import com.example.cabbooking.entity.User;
//import com.example.cabbooking.repository.DriverRepository;
//import com.example.cabbooking.repository.RideRepository;
//import com.example.cabbooking.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class RideServiceImpl implements RideService {
//
//    private final RideRepository rideRepo;
//    private final UserRepository userRepo;
//    private final DriverRepository driverRepo;
//
//    public RideServiceImpl(RideRepository rideRepo, UserRepository userRepo, DriverRepository driverRepo) {
//        this.rideRepo = rideRepo;
//        this.userRepo = userRepo;
//        this.driverRepo = driverRepo;
//    }
//
//    @Override
//    public Ride createRide(Ride ride, Long userId, Long driverId) {
//        // fetch user and driver
//        User user = userRepo.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
//        Driver driver = driverRepo.findByDriverId(driverId)
//                .orElseThrow(() -> new RuntimeException("Driver not found with id " + driverId));
//
//        ride.setUser(user);
//        ride.setDriver(driver);
//
//        // calculate fare
//        double baseFare = 50;
//        double perKm = 10;
//        double perMin = 2;
//        double calculatedFare = baseFare + (ride.getDistance() * perKm) + (ride.getTime() * perMin);
//        ride.setFare(calculatedFare);
//        ride.setStatus("REQUESTED");
//
//        return rideRepo.save(ride);
//    }
//
//    @Override
//    public List<RideDTO> getAllRidesDTO() {
//        return rideRepo.findAll().stream()
//                .map(r -> new RideDTO(
//                        r.getId(),
//                        (r.getUser() != null) ? r.getUser().getUserId() : null,
//                        (r.getDriver() != null) ? r.getDriver().getDriverId() : null,
//                        r.getPickup(),
//                        r.getDestination(),
//                        r.getDistance(),
//                        r.getTime(),
//                        r.getStatus(),
//                        r.getFare()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public RideDTO getRideByIdDTO(Long id) {
//        return rideRepo.findById(id)
//                .map(r -> new RideDTO(
//                        r.getId(),
//                        r.getUser().getUserId(),
//                        r.getDriver().getDriverId(),
//                        r.getPickup(),
//                        r.getDestination(),
//                        r.getDistance(),
//                        r.getTime(),
//                        r.getStatus(),
//                        r.getFare()
//                ))
//                .orElse(null);
//    }
//
//    @Override
//    public List<RideDTO> getRidesByUserIdDTO(Long userId) {
//        User user = userRepo.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
//        return rideRepo.findByUser(user).stream()
//                .map(r -> new RideDTO(
//                        r.getId(),
//                        r.getUser().getUserId(),
//                        r.getDriver().getDriverId(),
//                        r.getPickup(),
//                        r.getDestination(),
//                        r.getDistance(),
//                        r.getTime(),
//                        r.getStatus(),
//                        r.getFare()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<RideDTO> getRidesByDriverIdDTO(Long driverId) {
//        Driver driver = driverRepo.findById(driverId)
//                .orElseThrow(() -> new RuntimeException("Driver not found with id " + driverId));
//        return rideRepo.findByDriver(driver).stream()
//                .map(r -> new RideDTO(
//                        r.getId(),
//                        r.getUser().getUserId(),
//                        r.getDriver().getDriverId(),
//                        r.getPickup(),
//                        r.getDestination(),
//                        r.getDistance(),
//                        r.getTime(),
//                        r.getStatus(),
//                        r.getFare()
//                ))
//                .collect(Collectors.toList());
//    }
//}
package com.example.cabbooking.service;

import com.example.cabbooking.dto.RideDTO;
import com.example.cabbooking.entity.Driver;
import com.example.cabbooking.entity.Ride;
import com.example.cabbooking.entity.User;
import com.example.cabbooking.repository.DriverRepository;
import com.example.cabbooking.repository.RideRepository;
import com.example.cabbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {

    private final RideRepository rideRepo;
    private final UserRepository userRepo;
    private final DriverRepository driverRepo;
    private final Random random = new Random();

    public RideServiceImpl(RideRepository rideRepo, UserRepository userRepo, DriverRepository driverRepo) {
        this.rideRepo = rideRepo;
        this.userRepo = userRepo;
        this.driverRepo = driverRepo;
    }

    /**
     * Create a ride:
     * - Fetch user by userId
     * - Pick a random driver from DB
     * - Calculate fare
     * - Save ride
     */
    @Override
    public Ride createRide(Ride ride, Long userId) {
        // fetch user
        User user = userRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        // fetch all drivers
        List<Driver> drivers = driverRepo.findAll();
        if (drivers.isEmpty()) {
            throw new RuntimeException("No drivers available right now");
        }

        // pick random driver
        Driver driver = drivers.get(random.nextInt(drivers.size()));

        // set relations
        ride.setUser(user);
        ride.setDriver(driver);

        // calculate fare
        double baseFare = 50;
        double perKm = 10;
        double perMin = 2;
        double calculatedFare = baseFare + (ride.getDistance() * perKm) + (ride.getTime() * perMin);
        ride.setFare(calculatedFare);

        // default status
        if (ride.getStatus() == null) {
            ride.setStatus("REQUESTED");
        }

        return rideRepo.save(ride);
    }

    @Override
    public List<RideDTO> getAllRidesDTO() {
        return rideRepo.findAll().stream()
                .map(r -> new RideDTO(
                        r.getId(),
                        (r.getUser() != null) ? r.getUser().getUserId() : null,
                        (r.getDriver() != null) ? r.getDriver().getDriverId() : null,
                        r.getPickup(),
                        r.getDestination(),
                        r.getDistance(),
                        r.getTime(),
                        r.getStatus(),
                        r.getFare()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public RideDTO getRideByIdDTO(Long id) {
        return rideRepo.findById(id)
                .map(r -> new RideDTO(
                        r.getId(),
                        (r.getUser() != null) ? r.getUser().getUserId() : null,
                        (r.getDriver() != null) ? r.getDriver().getDriverId() : null,
                        r.getPickup(),
                        r.getDestination(),
                        r.getDistance(),
                        r.getTime(),
                        r.getStatus(),
                        r.getFare()
                ))
                .orElse(null);
    }

    @Override
    public List<RideDTO> getRidesByUserIdDTO(Long userId) {
        User user = userRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        return rideRepo.findByUser(user).stream()
                .map(r -> new RideDTO(
                        r.getId(),
                        (r.getUser() != null) ? r.getUser().getUserId() : null,
                        (r.getDriver() != null) ? r.getDriver().getDriverId() : null,
                        r.getPickup(),
                        r.getDestination(),
                        r.getDistance(),
                        r.getTime(),
                        r.getStatus(),
                        r.getFare()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<RideDTO> getRidesByDriverIdDTO(Long driverId) {
        Driver driver = driverRepo.findByDriverId(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with id " + driverId));
        return rideRepo.findByDriver(driver).stream()
                .map(r -> new RideDTO(
                        r.getId(),
                        (r.getUser() != null) ? r.getUser().getUserId() : null,
                        (r.getDriver() != null) ? r.getDriver().getDriverId() : null,
                        r.getPickup(),
                        r.getDestination(),
                        r.getDistance(),
                        r.getTime(),
                        r.getStatus(),
                        r.getFare()
                ))
                .collect(Collectors.toList());
    }

    public void deleteByUserIdAndDriverId(Long userId, Long driverId) {
        rideRepo.deleteByUserIdAndDriverId(userId, driverId);
    }
}




