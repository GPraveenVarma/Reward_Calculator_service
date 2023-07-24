package com.application.rewards_calculator.service;

import com.application.rewards_calculator.entity.Transaction;
import com.application.rewards_calculator.repository.TransactionRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class RewardServiceImp implements RewardService {
    private static final double REWARD_RATE_OVER_100 = 2;
    private static final double REWARD_RATE_BETWEEN_50_AND_100 = 1;

    private final TransactionRepository transactionRepository;

    public RewardServiceImp(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    /** Calculates the reward points based on the transaction amount. */

    @Override
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

    /**  Calculates the total reward points earned by a customer based on their transactions. */

    @Override
    public int totalPointsByCustomerId(Long customerId) {
        List<Transaction> transactions = transactionRepository
                .findByCustomerId(customerId);

        return transactions.stream()
                .map(transaction -> calculateRewardPoints(transaction.getTransactionAmount()))
                .reduce(0, (subtotal, element) -> subtotal + element);
    }

    /**  Calculates the total reward points earned by a customer between specific dates. */

    @Override
    public int totalPointsByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate) {

        List<Transaction> transactions = transactionRepository
                .findByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);

        return transactions.stream()
                .map(transaction -> calculateRewardPoints(transaction.getTransactionAmount()))
                .reduce(0, (subtotal, element) -> subtotal + element);
    }
}
