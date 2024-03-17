package com.app.tripPlanner.service;

import com.app.tripPlanner.dto.DestinationDto;
import com.app.tripPlanner.entity.Activity;
import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.repository.ActivityRepository;
import com.app.tripPlanner.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private ActivityRepository activityRepository;

    
    public DestinationDto findDestinationById(long id){
        try {
            Destination destination = destinationRepository.getById(id);
            DestinationDto destinationDto = new DestinationDto();
            destinationDto.setId(destination.getId());
            destinationDto.setName(destination.getName());
            destinationDto.setTravelPackageId(destination.getTravelPackage().getId());
            return destinationDto;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String addActivityInDestination(long destinationId, long activityId ){
        Destination destination = destinationRepository.getById(destinationId);
        Activity activity = activityRepository.getById(activityId);
        destination.addActivity(activity);
        return "Activty(" + activity + ") added in Destination(" + destination +")";
    }
}
