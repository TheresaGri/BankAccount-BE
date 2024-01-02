package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.GiroAccount;
import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.models.TransactionType;
import com.bankApplication.BankAccountBE.repositories.GiroAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GiroAccountService {
    private final GiroAccountRepository giroAccountRepository;
    private final TransactionService transactionService;

    public GiroAccountService(GiroAccountRepository giroAccountRepository, TransactionService transactionService) {
        this.giroAccountRepository = giroAccountRepository;
        this.transactionService = transactionService;
    }


    public GiroAccount getGiroAccountById(long userId) {
        return giroAccountRepository.findById(userId).orElse(null);
    }

    public Optional<GiroAccount> findGiroAccountByUserId(long userId) {
        return giroAccountRepository.findGiroAccountByUserId(userId);
    }

    public long getSumOfTransactionsOfGiroAccount(long userId) {
        GiroAccount giroAccountByUserId = giroAccountRepository.findById(userId).orElse(null);
        long sum = 0;
        List<Transaction> getTransactionsOfGiroAccountByUserId = giroAccountByUserId.getGiroAccountTransactions();
        if (giroAccountByUserId != null) {
            for (Transaction t : getTransactionsOfGiroAccountByUserId) {
                if (t.getTransactionType().equals(TransactionType.INCOMING)) {
                    sum += t.getTransactionAmount();
                } else {
                    sum -= t.getTransactionAmount();
                }
            }
        }
        return sum;
    }

    public void saveNewTransactionOfGiroAccount(long userId, Transaction transaction) {
        transaction.setDateAndTime(LocalDateTime.now());
        GiroAccount giroAccountByUserId = giroAccountRepository.findGiroAccountByUserId(userId).orElse(null);
        if(giroAccountByUserId != null) {
           // transactionService.saveTransaction(userId,transaction);
            List<Transaction> transactionList = giroAccountByUserId.getGiroAccountTransactions();
            transactionList.add(transaction);
            giroAccountByUserId.setGiroAccountTransactions(transactionList);
            giroAccountRepository.save(giroAccountByUserId);
        }
    }

}
