package com.app.tripPlanner.service;

import com.app.tripPlanner.entity.TravelPackage;
import com.app.tripPlanner.repository.TravelPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelPackageService {
    @Autowired
    private TravelPackageRepository travelPackageRepository;

    public TravelPackage findTravelPackageById(long id){
        return travelPackageRepository.getById(id);
    }
}
