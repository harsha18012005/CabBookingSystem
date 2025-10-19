//package com.example.cabbooking.service;
//
//import com.example.cabbooking.dto.RideDTO;
//import com.example.cabbooking.entity.Ride;
//
//import java.util.List;
//
//public interface RideService {
//    Ride createRide(Ride ride, Long userId);
//
//    List<RideDTO> getAllRidesDTO();
//
//    RideDTO getRideByIdDTO(Long id);
//
//    List<RideDTO> getRidesByUserIdDTO(Long userId);
//
//    List<RideDTO> getRidesByDriverIdDTO(Long driverId);
//    void deleteRide(Long id);
//}
package com.example.cabbooking.service;

import com.example.cabbooking.dto.RideDTO;
import com.example.cabbooking.entity.Ride;

import java.util.List;

public interface RideService {

    Ride createRide(Ride ride, Long userId); // create ride for a user

    List<RideDTO> getAllRidesDTO(); // get all rides as DTO

    RideDTO getRideByIdDTO(Long id); // get ride by id as DTO

    List<RideDTO> getRidesByUserIdDTO(Long userId); // get rides by user

    List<RideDTO> getRidesByDriverIdDTO(Long driverId); // get rides by driver

    void deleteRide(Long id); // delete ride
}

