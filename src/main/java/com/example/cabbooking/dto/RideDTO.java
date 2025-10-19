package com.example.cabbooking.dto;

import java.time.Instant;

public class RideDTO {
    private Long id;
    private Long userId;
    private Long driverId;
    private String pickup;
    private String destination;
    private double distance;
    private double time;
    private String status;
    private double fare;
    public RideDTO(Long id, Long userId, Long driverId, String pickup, String destination,
                   double distance, double time, String status,double fare){
        this.id = id;
        this.userId = userId;
        this.driverId = driverId;
        this.pickup = pickup;
        this.destination = destination;
        this.distance = distance;
        this.time = time;
        this.status = status;
        this.fare=fare;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}

