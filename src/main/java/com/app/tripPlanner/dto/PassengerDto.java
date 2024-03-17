package com.app.tripPlanner.dto;

import com.app.tripPlanner.constants.PassengerType;

public class PassengerDto {
    private long id;
    private String name;
    private String passengerNumber;
    private double balance;
    private PassengerType type;
    private long travelPackageId;

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(String passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public PassengerType getType() {
        return type;
    }

    public void setType(PassengerType type) {
        this.type = type;
    }

    public long getTravelPackageId() {
        return travelPackageId;
    }

    public void setTravelPackageId(long travelPackageId) {
        this.travelPackageId = travelPackageId;
    }
}
