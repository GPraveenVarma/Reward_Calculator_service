package com.application.rewards_calculator.api.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.application.rewards_calculator.entity.Transaction;
import com.application.rewards_calculator.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransactionRestControllerImpTest {

    private TransactionRepository transactionRepository;
    private TransactionRestControllerImp transactionRestController;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        transactionRestController = new TransactionRestControllerImp(transactionRepository);
    }

    @Test
    public void testAddTransaction() {
        Transaction transaction = new Transaction();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        ResponseEntity<String> responseEntity = transactionRestController.addTransaction(transaction);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Transaction added successfully.", responseEntity.getBody());

        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testGetTransaction() {
        List<Transaction> dummyTransactions = new ArrayList<>();
        dummyTransactions.add(new Transaction());
        dummyTransactions.add(new Transaction());

        when(transactionRepository.findAll()).thenReturn(dummyTransactions);

        ResponseEntity<List<Transaction>> responseEntity = transactionRestController.getTransaction();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dummyTransactions, responseEntity.getBody());

        verify(transactionRepository, times(1)).findAll();
    }
}
