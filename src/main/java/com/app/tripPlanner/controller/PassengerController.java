package com.app.tripPlanner.controller;

import com.app.tripPlanner.entity.Passenger;
import com.app.tripPlanner.service.ActivityService;
import com.app.tripPlanner.service.DestinationService;
import com.app.tripPlanner.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    private ActivityService activityService;


    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public Passenger findPassengerById(@RequestParam long id){
        return passengerService.findPassengerById(id);
    }

    @PostMapping("/sign-up-activity")
    public String signUpForActivity(@RequestParam Long passengerId, @RequestParam Long destinationId, @RequestParam Long activityId ){
        boolean activitySignUp = passengerService.signUpActivity(passengerId, destinationId, activityId);
        Passenger passenger = passengerService.findPassengerById(passengerId);
        String activityName = activityService.findActivityById(activityId).getName();
        String destinationName = destinationService.findDestinationById(activityId).getName();
        String response = "failed!";
        if(activitySignUp) response = "was successful.";
        return "Activity(" + activityName + ") sign up for Passenger(" + passenger.getName() + ", " +passenger.getPassengerNumber()  + ") at Destination(" + destinationName + ") " + response;
    }

    //Helper api for printing passenger details in  console
    @GetMapping("/print-passenger-details/{id}")
    public String printPassengerDetails(@PathVariable long id){
        Passenger passenger = passengerService.findPassengerById(id);
        passenger.printPassengerDetails();
        return "Printed details of Passenger(" + passenger.getName() +", " + passenger.getPassengerNumber() +") in console";
    }

}
