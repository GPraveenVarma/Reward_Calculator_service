package com.application.rewards_calculator.restcontroller;

import com.application.rewards_calculator.entity.Transaction;
import com.application.rewards_calculator.repository.TransactionRepository;
import com.application.rewards_calculator.service.RewardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class RewardsController {

    private final TransactionRepository transactionRepository;
    private final RewardsService rewardsService;

    public RewardsController(TransactionRepository transactionRepository, RewardsService rewardsService) {
        this.transactionRepository = transactionRepository;
        this.rewardsService = rewardsService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Transaction added successfully.");
    }
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransaction() {
        List<Transaction> all = transactionRepository.findAll();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/rewards/{customerId}/{year}/{month}")
    public ResponseEntity<Integer> getRewardsByCustomerIdAndMonth(
            @PathVariable Long customerId,
            @PathVariable int year,
            @PathVariable int month
    ) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        int totalPoints = rewardsService.totalPointsByCustomerIdAndDateBetween(customerId, startDate, endDate);
        return ResponseEntity.ok(totalPoints);
    }

    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<Integer> getRewardsByCustomerId(
            @PathVariable Long customerId
    ) {
        int totalPoints = rewardsService.totalPointsByCustomerId(customerId);
        return ResponseEntity.ok(totalPoints);
    }
}