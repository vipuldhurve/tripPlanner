package com.app.tripPlanner.controller;

import com.app.tripPlanner.dto.PassengerDto;
import com.app.tripPlanner.exception.ActivityCapacityReachedException;
import com.app.tripPlanner.exception.InsufficientBalanceException;
import com.app.tripPlanner.service.ActivityService;
import com.app.tripPlanner.service.DestinationService;
import com.app.tripPlanner.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PassengerDto> findPassengerById(@RequestParam long id) {
        PassengerDto passengerDto = passengerService.findPassengerById(id);
        if (passengerDto != null) {
            return ResponseEntity.ok(passengerDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/sign-up-activity")
    public String signUpForActivity(@RequestParam Long passengerId,
                                    @RequestParam Long destinationId,
                                    @RequestParam Long activityId ){
        try {
            boolean activitySignUp = passengerService.signUpActivity(passengerId, destinationId, activityId);
            PassengerDto passengerDto = passengerService.findPassengerById(passengerId);
            String activityName = activityService.findActivityById(activityId).getName();
            String destinationName = destinationService.findDestinationById(destinationId).getName();
            String response = "failed!";
            if(activitySignUp) response = "was successful.";
            return "Activity(" + activityName + ") sign up for Passenger(" + passengerDto.getName() + ", " +passengerDto.getPassengerNumber()  + ") at Destination(" + destinationName + ") " + response;
        } catch (InsufficientBalanceException | ActivityCapacityReachedException e){
            return e.getMessage();
        } catch (Exception e){
            return "Activity sign up failed! Please try again later.";
        }
    }

    //Helper api for printing passenger details in console
    @GetMapping("/print-passenger-details/{id}")
    public String printPassengerDetails(@PathVariable long id){
        return passengerService.printPassengerDetails(id);
    }

}
