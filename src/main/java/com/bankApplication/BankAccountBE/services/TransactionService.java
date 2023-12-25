package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findAllTransactionsByUserId(long userId) {
        return transactionRepository.findAllByUserId(userId);
    }

    public void saveTransaction(long userId, Transaction transaction) {
        transaction.setUserId(userId);
        transactionRepository.save(transaction);
    }
}
