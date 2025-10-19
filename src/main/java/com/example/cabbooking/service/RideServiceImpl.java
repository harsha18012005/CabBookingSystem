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
//import java.util.Random;
//import java.util.stream.Collectors;
//
//@Service
//public class RideServiceImpl implements RideService {
//
//    private final RideRepository rideRepo;
//    private final UserRepository userRepo;
//    private final DriverRepository driverRepo;
//    private final Random random = new Random();
//
//    public RideServiceImpl(RideRepository rideRepo, UserRepository userRepo, DriverRepository driverRepo) {
//        this.rideRepo = rideRepo;
//        this.userRepo = userRepo;
//        this.driverRepo = driverRepo;
//    }
//    @Override
//    public Ride createRide(Ride ride, Long userId) {
//        User user = userRepo.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
//        List<Driver> drivers = driverRepo.findAll();
//        if (drivers.isEmpty()) {
//            throw new RuntimeException("No drivers available right now");
//        }
//        Driver driver = drivers.get(random.nextInt(drivers.size()));
//        ride.setUser(user);
//        ride.setDriver(driver);
//        double baseFare = 50;
//        double perKm = 10;
//        double perMin = 2;
//        double calculatedFare = baseFare + (ride.getDistance() * perKm) + (ride.getTime() * perMin);
//        ride.setFare(calculatedFare);
//        if (ride.getStatus() == null) {
//            ride.setStatus("REQUESTED");
//        }
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
//                        (r.getUser() != null) ? r.getUser().getUserId() : null,
//                        (r.getDriver() != null) ? r.getDriver().getDriverId() : null,
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
//        User user = userRepo.findByUserId(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
//        return rideRepo.findByUser(user).stream()
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
//    public List<RideDTO> getRidesByDriverIdDTO(Long driverId) {
//        Driver driver = driverRepo.findByDriverId(driverId)
//                .orElseThrow(() -> new RuntimeException("Driver not found with id " + driverId));
//        return rideRepo.findByDriver(driver).stream()
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
//    public void deleteRide(Long id) {
//        rideRepo.deleteById(id);
//    }
//
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

    @Override
    public Ride createRide(Ride ride, Long userId) {
        User user = userRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        List<Driver> drivers = driverRepo.findAll();
        if (drivers.isEmpty()) {
            throw new RuntimeException("No drivers available right now");
        }

        Driver driver = drivers.get(random.nextInt(drivers.size()));

        ride.setUser(user);
        ride.setDriver(driver);
        double baseFare = 50;
        double perKm = 10;
        double perMin = 2;
        double calculatedFare = baseFare + (ride.getDistance() * perKm) + (ride.getTime() * perMin);
        ride.setFare(calculatedFare);
        if (ride.getTime() == 0) {
            ride.setTime((int)(ride.getDistance() * 2)); // example: 2 min per km
        }

        if (ride.getStatus() == null) {
            ride.setStatus("REQUESTED");
        }

        return rideRepo.save(ride);
    }

    @Override
    public List<RideDTO> getAllRidesDTO() {
        return rideRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RideDTO getRideByIdDTO(Long id) {
        return rideRepo.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public List<RideDTO> getRidesByUserIdDTO(Long userId) {
        User user = userRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        return rideRepo.findByUser(user).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RideDTO> getRidesByDriverIdDTO(Long driverId) {
        Driver driver = driverRepo.findByDriverId(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found with id " + driverId));
        return rideRepo.findByDriver(driver).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteRide(Long id) {
        rideRepo.deleteById(id);
    }

    // Helper to convert Ride entity to DTO
    private RideDTO convertToDTO(Ride r) {
        return new RideDTO(
                r.getId(),
                r.getUser() != null ? r.getUser().getUserId() : null,
                r.getDriver() != null ? r.getDriver().getDriverId() : null,
                r.getPickup(),
                r.getDestination(),
                r.getDistance(),
                r.getTime(),
                r.getStatus(),
                r.getFare()
        );
    }
}





