package com.example.cabbooking.controller;
import com.example.cabbooking.dto.RideDTO;
import com.example.cabbooking.entity.Ride;
import com.example.cabbooking.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    // Create a ride: pass userId as request param
    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride,
                                           @RequestParam Long userId) {
        Ride savedRide = rideService.createRide(ride, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRide);
    }

    // Get all rides
    @GetMapping
    public ResponseEntity<List<RideDTO>> getAllRides() {
        return ResponseEntity.ok(rideService.getAllRidesDTO());
    }

    // Get ride by id
    @GetMapping("/{id}")
    public ResponseEntity<RideDTO> getRideById(@PathVariable Long id) {
        RideDTO rideDTO = rideService.getRideByIdDTO(id);
        if (rideDTO != null) {
            return ResponseEntity.ok(rideDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get rides by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RideDTO>> getRidesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(rideService.getRidesByUserIdDTO(userId));
    }

    // Get rides by driver
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<RideDTO>> getRidesByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(rideService.getRidesByDriverIdDTO(driverId));
    }

    // Delete ride
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        rideService.deleteRide(id);
        return ResponseEntity.ok().build();


    }
}




