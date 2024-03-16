package com.app.tripPlanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private Double cost;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @JsonIgnore
    @ManyToMany(mappedBy = "activities")
    private List<Passenger> passengers;

    //Constructor
    public Activity() {} // Default constructor (required by JPA)

    public Activity(String name, String description, Double cost, Integer capacity, Destination destination, List<Passenger> passengers, Integer signedUpPassengers) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
        this.passengers = passengers; //empty initially
    }

    public Activity(String name, String description, Double cost, Integer capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    //Getters & Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
