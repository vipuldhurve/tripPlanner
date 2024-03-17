package com.app.tripPlanner.dto;

public class DestinationDto {
    private long id;
    private String name;
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

    public long getTravelPackageId() {
        return travelPackageId;
    }

    public void setTravelPackageId(long travelPackageId) {
        this.travelPackageId = travelPackageId;
    }
}
