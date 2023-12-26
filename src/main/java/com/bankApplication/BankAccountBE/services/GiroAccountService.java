package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.GiroAccount;
import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.models.TransactionType;
import com.bankApplication.BankAccountBE.repositories.GiroAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiroAccountService {
    private final GiroAccountRepository giroAccountRepository;

    public GiroAccountService(GiroAccountRepository giroAccountRepository) {
        this.giroAccountRepository = giroAccountRepository;
    }

    public GiroAccount getGiroAccountById(long userId) {
        return giroAccountRepository.findById(userId).orElse(null);
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

}
