package com.app.tripPlanner.service;

import com.app.tripPlanner.dto.TravelPackageDto;
import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.entity.Passenger;
import com.app.tripPlanner.entity.TravelPackage;
import com.app.tripPlanner.repository.DestinationRepository;
import com.app.tripPlanner.repository.PassengerRepository;
import com.app.tripPlanner.repository.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class TravelPackageService {
    @Autowired
    private TravelPackageRepository travelPackageRepository;

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public TravelPackageDto findTravelPackageById(long id){
        try{
            TravelPackage travelPackage = travelPackageRepository.getById(id);
            TravelPackageDto travelPackageDto = new TravelPackageDto();
            travelPackageDto.setId(travelPackage.getId());
            travelPackageDto.setName(travelPackage.getName());
            travelPackageDto.setPassengerCapacity(travelPackage.getPassengerCapacity());
            return travelPackageDto;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String addDestinationInTravelPackage(long packageId, long destinationId){
        Destination destination = destinationRepository.getById(destinationId);
        TravelPackage travelPackage = travelPackageRepository.getById(packageId);
        travelPackage.addDestination(destination);
        return "Destination(" + destination + ") added in Travel-Package(" + travelPackage+")";
    }

    public String addPassengerInTravelPackage(long packageId, long passengerId){
        Passenger passenger = passengerRepository.getById(passengerId);
        TravelPackage travelPackage = travelPackageRepository.getById(packageId);
        travelPackage.addPassenger(passenger);
        return "Passenger(" + passenger.getName() + ") added in Travel-Package(" + travelPackage +")";
    }

    //Helper method for printing itinerary of a travel package in console
    @GetMapping("/print-itinerary/{id}")
    public String printItinerary(long id){
        TravelPackage travelPackage = travelPackageRepository.getById(id);
        travelPackage.printItinerary();
        return "Printed itinerary of Travel-Package(" + travelPackage.getName() +") in console";
    }


    //Helper method for printing passenger list of a travel package in console
    @GetMapping("/print-passengers/{id}")
    public String printPassengers(long id){
        TravelPackage travelPackage = travelPackageRepository.getById(id);
        travelPackage.printPassengerList();
        return "Printed Passenger list of Travel-Package(" + travelPackage.getName() +") in console";
    }

    //Helper method for printing activities in console that still have spaces available in a travel package
    @GetMapping("/print-available-activities/{id}")
    public String printAvailableActivities(long id){
        TravelPackage travelPackage = travelPackageRepository.getById(id);
        travelPackage.printAvailableActivities();
        return "Printed activities that still have spaces available in Travel-Package(" + travelPackage.getName() +") in console";
    }
}
