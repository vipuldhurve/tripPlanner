package com.app.tripPlanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.tripPlanner.entity.TravelPackage;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
}
