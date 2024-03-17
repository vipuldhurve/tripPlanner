package com.app.tripPlanner.controller;

import com.app.tripPlanner.dto.ActivityDto;
import com.app.tripPlanner.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping
    public ResponseEntity<ActivityDto> findActivityById(@RequestParam long id) {
        ActivityDto activityDto = activityService.findActivityById(id);
        if (activityDto != null) {
            return ResponseEntity.ok(activityDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
