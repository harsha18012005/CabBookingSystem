package service;

import cab.demo.ride;
import cab.demo.cab;
import cab.demo.riderequest;
import cab.demo.user;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import repository.CabRepository;
import repository.RideRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepo;
    private final CabRepository cabRepo;

    // Constructor injection
    public RideService(RideRepository rideRepo, CabRepository cabRepo) {
        this.rideRepo = rideRepo;
        this.cabRepo = cabRepo;
    }


    public List<cab> searchAvailableCabs(String city) {
        // Assuming you have a method like this in CabRepository
        return cabRepo.findByCityAndStatus(city, "AVAILABLE");
    }

    public ride saveRide(ride ride) {
        return rideRepo.save(ride);
    }

    public @Nullable Object myRides(Long id) {
        return rideRepo.findByUserId(id);
    }

    public void equals(Long id, String status) {
    }

    public Object createRide(user user, @Valid riderequest req) {
        var ride=new ride();
        ride.setUser(user);
        ride.setPickup(req.getPickup());
        ride.setDropoff(req.getDropoff());
        return rideRepo.save(ride);
    }
}