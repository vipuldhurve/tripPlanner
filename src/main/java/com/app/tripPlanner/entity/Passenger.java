package com.app.tripPlanner.entity;

import com.app.tripPlanner.constants.PassengerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String passengerNumber;
    private double balance;
    @Enumerated(EnumType.STRING)
    private PassengerType type;

//    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackage travelPackage;

    @ManyToMany
    @JoinTable(name = "passenger_activity",
            joinColumns = @JoinColumn(name = "passenger_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private List<Activity> activities;

    //Constructor
    public Passenger() {} // Default constructor (required by JPA)

    public Passenger(String name, String passengerNumber, double balance, PassengerType type, TravelPackage travelPackage, List<Activity> activities) {
        this.name = name;
        this.passengerNumber = passengerNumber; // id = passengerNumber taken
        this.balance = balance;
        this.type = type;
        this.travelPackage = travelPackage;
        this.activities = activities; // empty initially
    }

    public Passenger(String name, String passengerNumber, double balance, PassengerType type) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.balance = balance;
        this.type = type;
    }

    //Print Passenger Details
    public void printPassengerDetails(){
        System.out.println("---- Printed Passenger Details:");
        System.out.println("Passenger Name: " + name);
        System.out.println("Passenger Number: "+ passengerNumber);
        System.out.println("Travel Subscription Type: "+ type.name());
        if (type!= PassengerType.PREMIUM){
            System.out.println("Balance: " + balance);
        }
        for(Activity activity: activities){
            System.out.println("Activity name: " + activity.getName() +", Cost: " + activity.getCost() + ", Destination: " + activity.getDestination());
        }
        System.out.println("-------------");
    }


    // Method to generate passenger number------------------------------
//    private String generatePassengerNumber(long id) {
//        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        StringBuilder sb = new StringBuilder();
//        sb.append(alphabet.charAt((int)(id / (26*26) % 26))); // First character
//        sb.append(alphabet.charAt((int)(id / 26 % 26))); // Second character
//        sb.append(alphabet.charAt((int)(id % 26))); // Third character
//        return sb.toString() + id;
//    }

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

    public TravelPackage getTravelPackage() {
        return travelPackage;
    }

    public void setTravelPackage(TravelPackage travelPackage) {
        this.travelPackage = travelPackage;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
