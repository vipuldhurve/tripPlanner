package com.app.tripPlanner.controller;

import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.entity.Passenger;
import com.app.tripPlanner.entity.TravelPackage;
import com.app.tripPlanner.service.DestinationService;
import com.app.tripPlanner.service.PassengerService;
import com.app.tripPlanner.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-package")
public class TravelPackageController {
    @Autowired
    private TravelPackageService travelPackageService;

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private PassengerService passengerService;

    @PostMapping("/add-destination")
    public String addDestinationInTravelPackage(@RequestParam Long packageId, @RequestParam Long destinationId){
        Destination destination = destinationService.findDestinationById(destinationId);
        TravelPackage travelPackage = travelPackageService.findTravelPackageById(packageId);
        travelPackage.addDestination(destination);
        return "Destination(" + destination + ") added in Travel-Package(" + travelPackage+")";
    }

    @PostMapping("/add-passenger")
    public String addPassengerInTravelPackage(@RequestParam Long packageId, @RequestParam Long passengerId){
        Passenger passenger = passengerService.findPassengerById(passengerId);
        TravelPackage travelPackage = travelPackageService.findTravelPackageById(packageId);
        travelPackage.addPassenger(passenger);
        return "Passenger(" + passenger.getName() + ") added in Travel-Package(" + travelPackage +")";
    }

    //Helper api for printing itinerary of a travel package in console
    @GetMapping("/print-itinerary/{id}")
    public String printItinerary(@PathVariable long id){
        TravelPackage travelPackage = travelPackageService.findTravelPackageById(id);
        System.out.println("---- Printed itinerary of travel package:");
        travelPackage.printItinerary();
        return "Printed itinerary of Travel-Package(" + travelPackage.getName() +") in console";
    }


    //Helper api for printing passenger list of a travel package in console
    @GetMapping("/print-passengers/{id}")
    public String printPassengers(@PathVariable long id){
        TravelPackage travelPackage = travelPackageService.findTravelPackageById(id);
        System.out.println("---- Printed passengers in the travel package:");
        travelPackage.printPassengerList();
        return "Printed Passenger list of Travel-Package(" + travelPackage.getName() +") in console";
    }

    //Helper api for printing activities in console that still have spaces available in a travel package
    @GetMapping("/print-available-activities/{id}")
    public String printAvailableActivities(@PathVariable long id){
        TravelPackage travelPackage = travelPackageService.findTravelPackageById(id);
        System.out.println("---- Printed available activities in the travel package:");
        travelPackage.printAvailableActivities();
        return "Printed activities that still have spaces available in Travel-Package(" + travelPackage.getName() +") in console";
    }


}
