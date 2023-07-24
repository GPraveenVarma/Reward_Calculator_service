package com.application.rewards_calculator.service;

import com.application.rewards_calculator.entity.Transaction;
import com.application.rewards_calculator.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RewardsServiceTest {


    private static TransactionRepository transactionRepository;

    private static RewardServiceImp rewardsService;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        rewardsService = new RewardServiceImp(transactionRepository);
    }

    @Test
    void testCalculateRewardPoints() {
        double transactionAmount1 = 120.0; // Expected points: 2 * $20 + 1 * $50 = 90
        double transactionAmount2 = 80.0;  // Expected points: 1 * $30 = 30

        int points1 = rewardsService.calculateRewardPoints(transactionAmount1);
        int points2 = rewardsService.calculateRewardPoints(transactionAmount2);

        assertEquals(90, points1);
        assertEquals(30, points2);
    }

    @Test
    void testTotalPointsByCustomerId() {
        long customerId = 123L;
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, customerId, LocalDate.of(2023, 7, 1), 120.0));
        transactions.add(new Transaction(2L, customerId, LocalDate.of(2023, 7, 15), 80.0));

        when(transactionRepository.findByCustomerId(eq(customerId))).thenReturn(transactions);

        int totalPoints = rewardsService.totalPointsByCustomerId(customerId);

        assertEquals(120, totalPoints); // Total points: 90 + 30 = 120
    }

    @Test
    void testTotalPointsByCustomerIdAndDateBetween() {
        long customerId = 123L;
        LocalDate startDate = LocalDate.of(2023, 7, 1);
        LocalDate endDate = LocalDate.of(2023, 7, 31);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, customerId, LocalDate.of(2023, 7, 1), 120.0));
        transactions.add(new Transaction(2L, customerId, LocalDate.of(2023, 7, 15), 80.0));

        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(eq(customerId), eq(startDate), eq(endDate)))
                .thenReturn(transactions);

        int totalPoints = rewardsService.totalPointsByCustomerIdAndDateBetween(customerId, startDate, endDate);

        assertEquals(120, totalPoints); // Total points: 90 + 30 = 120
    }

}