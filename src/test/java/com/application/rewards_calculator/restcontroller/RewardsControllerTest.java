package com.application.rewards_calculator.restcontroller;

import com.application.rewards_calculator.entity.Transaction;
import com.application.rewards_calculator.repository.TransactionRepository;
import com.application.rewards_calculator.service.RewardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class RewardsControllerTest {
    private TransactionRepository transactionRepository;
    private RewardsService rewardsService;
    private RewardsController rewardsController;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        rewardsService = mock(RewardsService.class);
        rewardsController = new RewardsController(transactionRepository, rewardsService);
    }

    @Test
     void testAddTransaction() {
        Transaction transaction = new Transaction();
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        ResponseEntity<String> responseEntity = rewardsController.addTransaction(transaction);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Transaction added successfully.", responseEntity.getBody());
    }

    @Test
     void testGetTransaction() {
        List<Transaction> transactions = new ArrayList<>();
        when(transactionRepository.findAll()).thenReturn(transactions);

        ResponseEntity<List<Transaction>> responseEntity = rewardsController.getTransaction();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactions, responseEntity.getBody());
    }

    @Test
     void testGetRewardsByCustomerIdAndMonth() {
        long customerId = 123L;
        int year = 2023;
        int month = 7;
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        int totalPoints = 100; // Mock the totalPoints value
        when(rewardsService.totalPointsByCustomerIdAndDateBetween(eq(customerId), eq(startDate), eq(endDate)))
                .thenReturn(totalPoints);

        ResponseEntity<Integer> responseEntity = rewardsController.getRewardsByCustomerIdAndMonth(customerId, year, month);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(totalPoints, responseEntity.getBody());
    }

    @Test
     void testGetRewardsByCustomerId() {
        long customerId = 123L;

        int totalPoints = 200; // Mock the totalPoints value
        when(rewardsService.totalPointsByCustomerId(eq(customerId))).thenReturn(totalPoints);

        ResponseEntity<Integer> responseEntity = rewardsController.getRewardsByCustomerId(customerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(totalPoints, responseEntity.getBody());
    }
}








