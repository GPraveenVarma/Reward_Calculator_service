package com.application.rewards_calculator.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface RewardRestController {
    /**
     below method handles incoming HTTP GET requests to retrieve the total rewards points earned
     by a specific customer within a given month and year.
     */
    @GetMapping("/rewards/{customerId}/{year}/{month}")
     ResponseEntity<Integer> getRewardsByCustomerIdAndMonth(
            @PathVariable Long customerId,
            @PathVariable int year,
            @PathVariable int month
    );

    /**
     below method handles incoming HTTP GET requests to retrieve the total rewards points earned
     by a specific customer.
     */
    @GetMapping("/rewards/{customerId}")
     ResponseEntity<Integer> getRewardsByCustomerId(
            @PathVariable Long customerId
    );
}
