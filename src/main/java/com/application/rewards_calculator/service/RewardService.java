package com.application.rewards_calculator.service;



import java.time.LocalDate;

public interface RewardService {
     int calculateRewardPoints(double transactionAmount);

     int totalPointsByCustomerId(Long customerId);

     int totalPointsByCustomerIdAndDateBetween(Long customerId, LocalDate startDate, LocalDate endDate);
}
