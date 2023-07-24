package com.application.rewards_calculator.imp;

import com.application.rewards_calculator.api.imp.RewardRestControllerImp;
import com.application.rewards_calculator.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RewardRestControllerImpTest {
    private RewardService rewardService;
    private RewardRestControllerImp rewardRestController;

    @BeforeEach
    public void setUp() {
        rewardService = mock(RewardService.class);
        rewardRestController = new RewardRestControllerImp(rewardService);
    }

    @Test
    void testGetRewardsByCustomerIdAndMonth() {
        long customerId = 123L;
        int year = 2023;
        int month = 7;

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        int totalPoints = 100; // Mock the totalPoints value
        when(rewardService.totalPointsByCustomerIdAndDateBetween(eq(customerId), eq(startDate), eq(endDate)))
                .thenReturn(totalPoints);

        ResponseEntity<Integer> responseEntity = rewardRestController.getRewardsByCustomerIdAndMonth(customerId, year, month);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(totalPoints, responseEntity.getBody());
    }

    @Test
    void testGetRewardsByCustomerId() {
        long customerId = 123L;

        int totalPoints = 200; // Mock the totalPoints value
        when(rewardService.totalPointsByCustomerId(eq(customerId))).thenReturn(totalPoints);

        ResponseEntity<Integer> responseEntity = rewardRestController.getRewardsByCustomerId(customerId);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(totalPoints, responseEntity.getBody());
    }
}