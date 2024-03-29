package com.app.tripPlanner.service;

import com.app.tripPlanner.dto.PassengerDto;
import com.app.tripPlanner.entity.Activity;
import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.entity.Passenger;
import com.app.tripPlanner.exception.ActivityCapacityReachedException;
import com.app.tripPlanner.exception.InsufficientBalanceException;
import com.app.tripPlanner.repository.ActivityRepository;
import com.app.tripPlanner.repository.DestinationRepository;
import com.app.tripPlanner.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PassengerService {

    private static final double DISCOUNT_TEN_PERCENT = 0.1;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ActivityRepository activityRepository;


    public PassengerDto findPassengerById(long id) {
        try {
            Passenger passenger = passengerRepository.getById(id);
            PassengerDto passengerDto = new PassengerDto();
            passengerDto.setId(passenger.getId());
            passengerDto.setName(passenger.getName());
            passengerDto.setBalance(passenger.getBalance());
            passengerDto.setPassengerNumber(passenger.getPassengerNumber());
            passengerDto.setType(passenger.getType());
            passengerDto.setTravelPackageId(passenger.getTravelPackage().getId());
            return passengerDto;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean signUpActivity(long passengerId, long destinationId, long activityId) throws Exception {
        try {
            //fetch passenger details
            Passenger passenger = passengerRepository.getById(passengerId);

            //fetch destination details
            Destination destination = destinationRepository.getById(destinationId);

            // fetch activity details available in destination
            Activity activity = activityRepository.getById(activityId);

            //SignUP
            //Add activity in activityList Of passenger
            //Add passenger in passengerList of activity
            if (activity.getPassengers().size() < activity.getCapacity()) {
                switch (passenger.getType()) {
                    case STANDARD:
                        return signUpActivityStandardPassenger(passenger, activity);
                    case GOLD:
                        return signUpActivityGoldPassenger(passenger, activity);
                    case PREMIUM:
                        return signUpActivityPremiumPassenger(passenger, activity);
                }
            } else {
                throw new ActivityCapacityReachedException("Activity " + activity.getName() + " has reached its capacity. Cannot sign up.");
            }
        } catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
            throw new InsufficientBalanceException(e.getMessage());
        }
        catch (ActivityCapacityReachedException e){
            System.out.println(e.getMessage());
            throw new ActivityCapacityReachedException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return false;
    }

    private boolean signUpActivityStandardPassenger(Passenger passenger, Activity activity) throws InsufficientBalanceException {
        double activityCost = activity.getCost();
        if (passenger.getBalance() >= activityCost) {
            passenger.setBalance(passenger.getBalance() - activityCost);
            passenger.getActivities().add(activity);
            activity.getPassengers().add(passenger);
            passengerRepository.save(passenger);
            activityRepository.save(activity);
            System.out.println(passenger.getName() + " (Standard Passenger) signed up for " + activity.getName());
            return true;
        } else {
            throw new InsufficientBalanceException("Insufficient balance for " + passenger.getName() + " to sign up for " + activity.getName());
        }
    }


    private boolean signUpActivityGoldPassenger(Passenger passenger, Activity activity) throws InsufficientBalanceException {
        double activityCost = activity.getCost();
        double discountedCost = applyDiscount(activityCost, DISCOUNT_TEN_PERCENT);
        if (passenger.getBalance() >= discountedCost) {
            passenger.setBalance(passenger.getBalance() - discountedCost);
            passenger.getActivities().add(activity);
            activity.getPassengers().add(passenger);
            passengerRepository.save(passenger);
            activityRepository.save(activity);
            System.out.println(passenger.getName() + " (Gold Passenger) signed up for " + activity.getName() + " with " + (DISCOUNT_TEN_PERCENT * 100) + "% discount");
            return true;
        } else {
            throw new InsufficientBalanceException("Insufficient balance for " + passenger.getName() + " to sign up for " + activity.getName());
        }
    }

    private boolean signUpActivityPremiumPassenger(Passenger passenger, Activity activity) {
        // Premium passengers can sign up for activities for free
        System.out.println(passenger.getName() + " (Premium Passenger) signed up for " + activity.getName() + " for free");
        passengerRepository.save(passenger);
        activityRepository.save(activity);
        return true;
    }

    private double applyDiscount(double cost, double discount) {
        return cost * (1 - discount);
    }

    public String printPassengerDetails(@PathVariable long id){
        Passenger passenger = passengerRepository.getById(id);
        passenger.printPassengerDetails();
        return "Printed details of Passenger(" + passenger.getName() +", " + passenger.getPassengerNumber() +") in console";
    }
}
