package com.application.rewards_calculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction implements Serializable {
    @Id
    private Long id;
    private Long customerId;
    private LocalDate transactionDate;
    private double transactionAmount;

}
