//
//package com.example.cabbooking.controller;
//
//import com.example.cabbooking.dto.RideDTO;
//import com.example.cabbooking.entity.Ride;
//import com.example.cabbooking.service.RideService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/api/rides")
//public class RideController {
//
//    private final RideService rideService;
//
//    public RideController(RideService rideService) {
//        this.rideService = rideService;
//    }
//
//    @PostMapping
//    public ResponseEntity<Ride> createRide(@RequestBody Ride ride,
//                                           @RequestParam Long userId,
//                                           @RequestParam Long driverId) {
//        Ride savedRide = rideService.createRide(ride, userId, driverId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedRide);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<RideDTO>> getAllRides() {
//        return ResponseEntity.ok(rideService.getAllRidesDTO());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<RideDTO> getRideById(@PathVariable Long id) {
//        RideDTO rideDTO = rideService.getRideByIdDTO(id);
//        if (rideDTO != null) return ResponseEntity.ok(rideDTO);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<RideDTO>> getRidesByUser(@PathVariable Long userId) {
//        return ResponseEntity.ok(rideService.getRidesByUserIdDTO(userId));
//    }
//
//    @GetMapping("/driver/{driverId}")
//    public ResponseEntity<List<RideDTO>> getRidesByDriver(@PathVariable Long driverId) {
//        return ResponseEntity.ok(rideService.getRidesByDriverIdDTO(driverId));
//    }
//}

package com.example.cabbooking.controller;

import com.example.cabbooking.dto.RideDTO;
import com.example.cabbooking.entity.Ride;
import com.example.cabbooking.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    /**
     * Create a new ride.
     * Only userId is passed from frontend.
     * Driver is assigned randomly in service layer.
     */
    @PostMapping
    public ResponseEntity<Ride> createRide(@RequestBody Ride ride,
                                           @RequestParam Long userId) {
        Ride savedRide = rideService.createRide(ride, userId); // driverId not needed
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRide);
    }

    @GetMapping
    public ResponseEntity<List<RideDTO>> getAllRides() {
        return ResponseEntity.ok(rideService.getAllRidesDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RideDTO> getRideById(@PathVariable Long id) {
        RideDTO rideDTO = rideService.getRideByIdDTO(id);
        if (rideDTO != null) {
            return ResponseEntity.ok(rideDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RideDTO>> getRidesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(rideService.getRidesByUserIdDTO(userId));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<RideDTO>> getRidesByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(rideService.getRidesByDriverIdDTO(driverId));
    }
    @DeleteMapping("/rides")
    public ResponseEntity<?> deleteRide(@RequestParam Long userId, @RequestParam Long driverId){
        rideService.deleteByUserIdAndDriverId(userId, driverId);
        return ResponseEntity.ok().build();
    }

}



