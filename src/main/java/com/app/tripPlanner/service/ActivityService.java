package com.app.tripPlanner.service;

import com.app.tripPlanner.entity.Activity;
import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.entity.Passenger;
import com.app.tripPlanner.repository.ActivityRepository;
import com.app.tripPlanner.repository.DestinationRepository;
import com.app.tripPlanner.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public Activity findActivityById(long id){
        return activityRepository.getById(id);
    }
}
