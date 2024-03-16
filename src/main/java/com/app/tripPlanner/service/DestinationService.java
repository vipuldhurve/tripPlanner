package com.app.tripPlanner.service;

import com.app.tripPlanner.entity.Activity;
import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.repository.ActivityRepository;
import com.app.tripPlanner.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;
    
    public Destination findDestinationById(long id){
        return destinationRepository.getById(id);
    }
}
