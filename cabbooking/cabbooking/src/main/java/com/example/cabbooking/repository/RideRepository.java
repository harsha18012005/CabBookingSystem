//package com.example.cabbooking.repository;
//
//import com.example.cabbooking.entity.Ride;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface RideRepository extends JpaRepository<Ride, Long> {
//    List<Ride> findByUserId(Long userId);
//    List<Ride> findByDriverId(Long driverId);
//}
package com.example.cabbooking.repository;

import com.example.cabbooking.entity.Ride;
import com.example.cabbooking.entity.User;
import com.example.cabbooking.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByUser(User user);
    List<Ride> findByDriver(Driver driver);
    void deleteByUserIdAndDriverId(Long userId, Long driverId);
}

