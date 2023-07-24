package com.application.rewards_calculator.api.imp;

import com.application.rewards_calculator.api.RewardRestController;
import com.application.rewards_calculator.repository.TransactionRepository;
import com.application.rewards_calculator.service.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class RewardRestControllerImp implements RewardRestController {

    private RewardService rewardService;

    public RewardRestControllerImp(RewardService rewardService) {
        this.rewardService = rewardService;
    }
    @Override
    public ResponseEntity<Integer> getRewardsByCustomerIdAndMonth(
            @PathVariable Long customerId,
            @PathVariable int year,
            @PathVariable int month
    ) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        int totalPoints = rewardService.totalPointsByCustomerIdAndDateBetween(customerId, startDate, endDate);
        return ResponseEntity.ok(totalPoints);
    }

    @Override
    public ResponseEntity<Integer> getRewardsByCustomerId(
            @PathVariable Long customerId
    ) {
        int totalPoints = rewardService.totalPointsByCustomerId(customerId);
        return ResponseEntity.ok(totalPoints);
    }
}
