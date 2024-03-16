package com.app.tripPlanner.repository;

import com.app.tripPlanner.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
