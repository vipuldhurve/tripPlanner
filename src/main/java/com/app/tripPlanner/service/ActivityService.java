package com.app.tripPlanner.service;

import com.app.tripPlanner.dto.ActivityDto;
import com.app.tripPlanner.entity.Activity;
import com.app.tripPlanner.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public ActivityDto findActivityById(long id){
        try {
            Activity activity = activityRepository.getById(id);
            ActivityDto activityDto = new ActivityDto();
            activityDto.setId(activity.getId());
            activityDto.setName(activity.getName());
            activityDto.setCapacity(activity.getCapacity());
            activityDto.setCost(activity.getCost());
            activityDto.setDescription(activity.getDescription());
            activityDto.setDestinationId(activity.getDestination().getId());
            return activityDto;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
