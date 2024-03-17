package com.app.tripPlanner.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "travel_package")
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Destination> itinerary;

    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL)
    private List<Passenger> passengers;

    //Constructor
    public TravelPackage() {
    } // Default constructor (required by JPA)

    public TravelPackage(String name, int passengerCapacity, List<Destination> itinerary, List<Passenger> passengers) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.itinerary = itinerary;
        this.passengers = passengers;
    }

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
    }

    //Methods
    //Print itinerary - destination and activities available in a travel package
    public void printItinerary(){
        System.out.println("---- Printed itinerary of travel package:");
        System.out.println("Travel Package: " + name);
        for(Destination destination: itinerary){
            System.out.println("Destination: " + destination.getName());
            for(Activity activity: destination.getActivities()){
                System.out.println("Activity: " + activity.getName() + ", Cost: "+ activity.getCost() + ", Capacity: "+ activity.getCapacity() + ", Description: "+activity.getDescription());
            }
        }
        System.out.println("-------------");
    }

    //Print Passenger List of a travel package
    public void printPassengerList(){
        System.out.println("---- Printed passengers in the travel package:");
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: "+ passengerCapacity);
        System.out.println("Number of passengers enrolled: " + passengers.size());
        //Print passenger details
        for(Passenger passenger: passengers){
            System.out.println("Name: "+ passenger.getName() + ", Passenger Number: " + passenger.getPassengerNumber());
        }
        System.out.println("-------------");
    }

    //Print the details of all the activities that still have spaces available
    // including how many spaces are available
    public void printAvailableActivities(){
        System.out.println("---- Printed available activities in the travel package:");
        System.out.println("Travel Package: " + name);
        for(Destination destination: itinerary){
            System.out.println("Destination: " + destination.getName());
            for(Activity activity: destination.getActivities()){
                int capacity = activity.getCapacity();
                int noOfPassengers = activity.getPassengers().size();
                if(noOfPassengers<capacity){
                    int availableSpaces = capacity - noOfPassengers;
                    System.out.println("Activity: " + activity.getName() + ", Cost: "+ activity.getCost() + ", Capacity: "+ activity.getCapacity() + ", Spaces Available: " + availableSpaces +", Description: "+activity.getDescription());
                }
            }
        }
        System.out.println("-------------");
    }

    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
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

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public List<Destination> getItinerary() {
        return itinerary;
    }

    public void setItinerary(List<Destination> itinerary) {
        this.itinerary = itinerary;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

}
