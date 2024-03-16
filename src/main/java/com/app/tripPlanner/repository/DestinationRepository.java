package com.app.tripPlanner.repository;

import com.app.tripPlanner.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
