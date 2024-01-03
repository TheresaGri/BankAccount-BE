package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.exceptions.NoTransactionByThisUserIdFound;
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

    public long getSumOfTransactionsOfGiroAccount(long userId)  {
        //TODO: implement exception
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

    public void saveNewTransactionOnGiroAccountWithSavingAccount(long userId, Transaction transaction) {
        //TODO: implement exception
        transaction.setDateAndTime(LocalDateTime.now());
        GiroAccount giroAccountByUserId = giroAccountRepository.findGiroAccountByUserId(userId).orElse(null);
        if (giroAccountByUserId != null) {
            List<Transaction> transactionList = giroAccountByUserId.getGiroAccountTransactions();
            transactionList.add(transaction);
            giroAccountByUserId.setGiroAccountTransactions(transactionList);
            giroAccountRepository.save(giroAccountByUserId);
        }
    }

    public void saveNewTransactionOnGiroAccount(long userId, Transaction transaction) {
        transaction.setTransactionType(TransactionType.OUTGOING);
        transactionService.saveTransaction(userId, transaction);
        saveNewTransactionOnGiroAccountWithSavingAccount(userId, transaction);
    }

}
