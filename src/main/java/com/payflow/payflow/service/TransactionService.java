package com.payflow.payflow.service;

import com.payflow.payflow.entity.Transaction;
import com.payflow.payflow.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    // Spring does dependency injection at startup, wiring the TransactionRepository bean into this service bean.
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction sendMoney(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
