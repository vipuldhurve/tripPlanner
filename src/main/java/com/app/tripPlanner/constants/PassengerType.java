package com.app.tripPlanner.constants;

public enum PassengerType {
    STANDARD, // balance < 5000
    GOLD,  // balance >= 5000 && balance < 10,000
    PREMIUM //  balance >= 10,000
}
