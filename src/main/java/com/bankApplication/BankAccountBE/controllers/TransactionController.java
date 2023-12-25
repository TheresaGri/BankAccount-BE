package com.bankApplication.BankAccountBE.controllers;

import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "http://localhost:5173")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    public List<Transaction> getAllTransactionsByUserId(@PathVariable long userId) {
        return transactionService.findAllTransactionsByUserId(userId);
    }

    @PostMapping("/{userId}")
    public void saveNewTransaction (@PathVariable long userId, @RequestBody Transaction newTransaction) {
        transactionService.saveTransaction(userId,newTransaction);
    }
}
