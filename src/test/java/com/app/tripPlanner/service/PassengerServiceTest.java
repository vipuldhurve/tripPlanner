package com.app.tripPlanner.service;

import com.app.tripPlanner.constants.PassengerType;
import com.app.tripPlanner.entity.Activity;
import com.app.tripPlanner.entity.Destination;
import com.app.tripPlanner.entity.Passenger;
import com.app.tripPlanner.exception.ActivityCapacityReachedException;
import com.app.tripPlanner.exception.InsufficientBalanceException;
import com.app.tripPlanner.repository.ActivityRepository;
import com.app.tripPlanner.repository.DestinationRepository;
import com.app.tripPlanner.repository.PassengerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private PassengerService passengerService;

    private Passenger passenger;
    private Activity activity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        passenger = new Passenger();
        passenger.setBalance(200); // Sufficient balance
        passenger.setActivities(new ArrayList<>());
        activity = new Activity();
        activity.setCost(100.0); // Set activity cost
        activity.setCapacity(10); // Set activity capacity
        activity.setPassengers(new ArrayList<>());
        when(destinationRepository.getById(1L)).thenReturn(new Destination());
        when(passengerRepository.getById(1L)).thenReturn(passenger);
        when(activityRepository.getById(1L)).thenReturn(activity);
    }

    @Test
    public void testFindPassengerById() {
        long passengerId = 1;
        when(passengerRepository.getById(passengerId)).thenReturn(passenger);
        assertEquals(passenger, passengerService.findPassengerById(passengerId));
    }

    @Test
    public void testSignUpActivityStandardPassenger_Successful() throws Exception {
        passenger.setType(PassengerType.STANDARD);
        boolean result = passengerService.signUpActivity(1L, 1L, 1L);
        assertTrue(result);
    }

    @Test
    public void testSignUpActivityGoldPassenger_Successful() throws Exception {
        passenger.setType(PassengerType.GOLD);
        boolean result = passengerService.signUpActivity(1L, 1L, 1L);
        assertTrue(result);
    }

    @Test
    public void testSignUpActivityPremiumPassenger_Successful() throws Exception {
        long passengerId = 1;
        long destinationId = 1;
        long activityId = 1;
        passenger.setType(PassengerType.PREMIUM);
        boolean result = passengerService.signUpActivity(1L, 1L, 1L);
        assertTrue(result);
    }

    @Test
    public void testSignUpActivity_ActivityCapacityReached() {
        long passengerId = 1;
        long destinationId = 1;
        long activityId = 1;
        passenger.setType(PassengerType.STANDARD);
        activity.setCapacity(0); // Set capacity to 0 to simulate activity at full capacity
        assertThrows(ActivityCapacityReachedException.class, () -> {
            passengerService.signUpActivity(passengerId, destinationId, activityId);
        });
    }

    @Test
    public void testSignUpActivityStandardPassenger_InsufficientBalance() {
        passenger.setType(PassengerType.STANDARD);
        passenger.setBalance(50); // Set balance less than activity cost
        assertThrows(InsufficientBalanceException.class, () -> {
            passengerService.signUpActivity(1L, 1L, 1L);
        });
    }

    @Test
    public void testSignUpActivityGoldPassenger_InsufficientBalance() {
        passenger.setType(PassengerType.GOLD);
        passenger.setBalance(50); // Set balance less than activity cost
        assertThrows(InsufficientBalanceException.class, () -> {
            passengerService.signUpActivity(1L, 1L, 1L);
        });
    }

    @Test
    public void testSignUpActivity_Exception() {
        activity.setPassengers(null);
        assertThrows(Exception.class, () -> {
            passengerService.signUpActivity(1L, 1L, 1L);
        });
    }


}
