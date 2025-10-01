
package com.example.cabbooking.service;

import com.example.cabbooking.dto.RideDTO;
import com.example.cabbooking.entity.Ride;

import java.util.List;

public interface RideService {

//    Ride createRide(Ride ride, Long userId, Long driverId);
    Ride createRide(Ride ride, Long userId);

    List<RideDTO> getAllRidesDTO();

    RideDTO getRideByIdDTO(Long id);

    List<RideDTO> getRidesByUserIdDTO(Long userId);

    List<RideDTO> getRidesByDriverIdDTO(Long driverId);
    void deleteByUserIdAndDriverId(Long userId, Long driverId);
}
