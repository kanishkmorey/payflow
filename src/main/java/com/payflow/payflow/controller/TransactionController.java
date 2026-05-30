package com.payflow.payflow.controller;

import com.payflow.payflow.entity.Transaction;
import com.payflow.payflow.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public Transaction sendMoney(@RequestBody Transaction transaction) {
        return transactionService.sendMoney(transaction);
    }
}
