package com.app.tripPlanner.repository;

import com.app.tripPlanner.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
