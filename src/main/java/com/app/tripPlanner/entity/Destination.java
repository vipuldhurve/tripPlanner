package com.app.tripPlanner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "destination")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "travel_package_id")
    private TravelPackage travelPackage;

    @JsonIgnore
    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Activity> activities;

    //Constructor
    public Destination() {} // Default constructor (required by JPA)

    public Destination(String name) {
        this.name = name;
    }

    public Destination(String name, TravelPackage travelPackage, List<Activity> activities) {
        this.name = name;
        this.travelPackage = travelPackage;
        this.activities = activities;
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

    //Methods
    public void addActivity(Activity activity){
        activities.add(activity);
    }
}
