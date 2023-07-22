package com.application.rewards_calculator.service;

import com.application.rewards_calculator.entity.Transaction;
import com.application.rewards_calculator.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RewardsService  {
    private static final double REWARD_RATE_OVER_100 = 2;
    private static final double REWARD_RATE_BETWEEN_50_AND_100 = 1;

    private final TransactionRepository transactionRepository;


    public RewardsService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public int calculateRewardPoints(double transactionAmount) {
        int points = 0;
        if (transactionAmount > 100) {
            points += (int) ((transactionAmount - 100) * REWARD_RATE_OVER_100);
        }
        if (transactionAmount > 50) {
            points += (int) (Math.min(transactionAmount, 100) - 50) * REWARD_RATE_BETWEEN_50_AND_100;
        }
        return points;
    }

    public int totalPointsByCustomerId(Long customerId) {

        List<Transaction> transactions = transactionRepository
                .findByCustomerId(customerId);

        return transactions.stream()
                .map(transaction -> calculateRewardPoints(transaction.getTransactionAmount()))
                .reduce(0, (subtotal, element) -> subtotal + element);
    }

    public int totalPointsByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate) {

        List<Transaction> transactions = transactionRepository
                .findByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);

        return transactions.stream()
                .map(transaction -> calculateRewardPoints(transaction.getTransactionAmount()))
                .reduce(0, (subtotal, element) -> subtotal + element);
    }

}
