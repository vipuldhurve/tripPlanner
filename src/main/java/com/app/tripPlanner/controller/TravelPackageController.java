package com.app.tripPlanner.controller;

import com.app.tripPlanner.dto.TravelPackageDto;
import com.app.tripPlanner.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel-package")
public class TravelPackageController {
    @Autowired
    private TravelPackageService travelPackageService;

    @GetMapping
    public ResponseEntity<TravelPackageDto> findTravelPackageById(@RequestParam long id){
        TravelPackageDto travelPackageDto = travelPackageService.findTravelPackageById(id);
        if (travelPackageDto != null) {
            return ResponseEntity.ok(travelPackageDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add-destination")
    public String addDestinationIn(@RequestParam long packageId, @RequestParam long destinationId){
        return travelPackageService.addDestinationInTravelPackage(packageId, destinationId);
    }

    @PostMapping("/add-passenger")
    public String addPassenger(@RequestParam long packageId, @RequestParam long passengerId){
        return travelPackageService.addPassengerInTravelPackage(packageId, passengerId);
    }

    //Helper api for printing itinerary of a travel package in console
    @GetMapping("/print-itinerary/{id}")
    public String printItinerary(@PathVariable long id){
        return travelPackageService.printItinerary(id);
    }

    //Helper api for printing passenger list of a travel package in console
    @GetMapping("/print-passengers/{id}")
    public String printPassengers(@PathVariable long id){
        return travelPackageService.printPassengers(id);
    }

    //Helper api for printing activities in console that still have spaces available in a travel package
    @GetMapping("/print-available-activities/{id}")
    public String printAvailableActivities(@PathVariable long id){
        return travelPackageService.printAvailableActivities(id);
    }

}
