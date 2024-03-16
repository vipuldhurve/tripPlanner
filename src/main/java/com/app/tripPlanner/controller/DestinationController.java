package com.app.tripPlanner.controller;

import com.app.tripPlanner.entity.Activity;
import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.service.ActivityService;
import com.app.tripPlanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ActivityService activityService;

    @PostMapping("/add-activity")
    public String addActivityInDestination(@RequestParam Long destinationId, @RequestParam Long activityId ){
        Destination destination = destinationService.findDestinationById(destinationId);
        Activity activity = activityService.findActivityById(activityId);
        destination.addActivity(activity);
        return "Activty(" + activity + ") added in Destination(" + destination +")";
    }

}
