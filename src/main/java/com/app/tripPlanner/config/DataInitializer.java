package com.app.tripPlanner.config;

import com.app.tripPlanner.constants.PassengerType;
import com.app.tripPlanner.entity.Activity;
import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.entity.Passenger;
import com.app.tripPlanner.entity.TravelPackage;
import com.app.tripPlanner.repository.ActivityRepository;
import com.app.tripPlanner.repository.DestinationRepository;
import com.app.tripPlanner.repository.PassengerRepository;
import com.app.tripPlanner.repository.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @PostConstruct
    public void init() {
        // Create and save travel packages
        TravelPackage package1 = new TravelPackage("TP 1", 10);
        travelPackageRepository.save(package1);

        // Create and save destinations
        Destination destination1 = new Destination("Destination 1");
        destination1.setTravelPackage(package1);
        destinationRepository.save(destination1);


        // Create and save activities
        Activity activity1 = new Activity("Activity 1X", "Description 1X", 1000.0, 5);
        activity1.setDestination(destination1);
        activityRepository.save(activity1);
        //
        Activity activity2 = new Activity("Activity 2X", "Description 2X", 3000.0, 3);
        activity2.setDestination(destination1);
        activityRepository.save(activity2);
        //
        Activity activity3 = new Activity("Activity 3X", "Description 3X", 7000.0, 1);
        activity3.setDestination(destination1);
        activityRepository.save(activity3);


        // Create and save passengers
        Passenger standardPassenger1 = new Passenger("Raju", "ABC111", 2000, PassengerType.STANDARD);
        standardPassenger1.setTravelPackage(package1);
        passengerRepository.save(standardPassenger1);
        //
        Passenger goldPassenger1 = new Passenger("Farhan", "ABC222", 6000, PassengerType.GOLD);
        goldPassenger1.setTravelPackage(package1);
        passengerRepository.save(goldPassenger1);
        //
        Passenger premiumPassenger1 = new Passenger("Rancho", "ABC333", 12000, PassengerType.PREMIUM);
        premiumPassenger1.setTravelPackage(package1);
        passengerRepository.save(premiumPassenger1);
    }


}