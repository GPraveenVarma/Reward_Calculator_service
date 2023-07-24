package com.application.rewards_calculator.api;

import com.application.rewards_calculator.entity.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TransactionRestController {

    /** below method handles incoming HTTP POST requests to add a new transaction to the system. */
     @PostMapping("/transactions")
     ResponseEntity<String> addTransaction(@RequestBody Transaction transaction);

    /** below method returns all transactions from the system.  */
     @GetMapping("/transactions")
     ResponseEntity<List<Transaction>> getTransaction();
}
