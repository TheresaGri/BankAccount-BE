package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.SavingAccount;
import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.models.TransactionType;
import com.bankApplication.BankAccountBE.repositories.SavingAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingAccountService {

    private final SavingAccountRepository savingAccountRepository;

    public SavingAccountService(SavingAccountRepository savingAccountRepository) {
        this.savingAccountRepository = savingAccountRepository;
    }

    public Optional<SavingAccount> findSavingAccountByUserId(long userId) {
        return savingAccountRepository.findSavingAccountByUserId(userId);
    }

    public long getSumOfTransactionsOfSavingAccount(long userId) {
        SavingAccount savingAccountByUserId = savingAccountRepository.findSavingAccountByUserId(userId).orElse(null);
        long sum = 0;
        List<Transaction> getTransactionOfSavingAccountByUserId = savingAccountByUserId.getSavingAccountTransactionList();
        if (savingAccountByUserId != null) {
            for (Transaction t : getTransactionOfSavingAccountByUserId) {
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
