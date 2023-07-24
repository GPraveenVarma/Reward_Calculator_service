package com.application.rewards_calculator.api.imp;

import com.application.rewards_calculator.api.TransactionRestController;
import com.application.rewards_calculator.entity.Transaction;
import com.application.rewards_calculator.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionRestControllerImp implements TransactionRestController {
    private TransactionRepository transactionRepository;

    public TransactionRestControllerImp(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.ok("Transaction added successfully.");
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransaction() {
        List<Transaction> all = transactionRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
