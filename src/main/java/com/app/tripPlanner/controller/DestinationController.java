package com.app.tripPlanner.controller;

import com.app.tripPlanner.dto.DestinationDto;
import com.app.tripPlanner.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping
    public ResponseEntity<DestinationDto> findDestinationById(@RequestParam long id) {
        DestinationDto destinationDto = destinationService.findDestinationById(id);
        if (destinationDto != null) {
            return ResponseEntity.ok(destinationDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add-activity")
    public String addActivityInDestination(@RequestParam long destinationId, @RequestParam long activityId ){
        return destinationService.addActivityInDestination(destinationId, activityId);
    }

}
