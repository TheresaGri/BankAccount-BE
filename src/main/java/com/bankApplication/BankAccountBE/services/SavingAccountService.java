package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.SavingAccount;
import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.models.TransactionType;
import com.bankApplication.BankAccountBE.repositories.SavingAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SavingAccountService {

    private final SavingAccountRepository savingAccountRepository;
    private final TransactionService transactionService;
    private final GiroAccountService giroAccountService;

    public SavingAccountService(SavingAccountRepository savingAccountRepository, TransactionService transactionService, GiroAccountService giroAccountService) {
        this.savingAccountRepository = savingAccountRepository;
        this.transactionService = transactionService;
        this.giroAccountService = giroAccountService;
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

    public void addNewTransactionToSavingAccount(long userId, Transaction transaction) {
        SavingAccount savingAccountByUserId = savingAccountRepository.findSavingAccountByUserId(userId).orElse(null);
        if (savingAccountByUserId != null) {
            List<Transaction> transactionList = savingAccountByUserId.getSavingAccountTransactionList();
            transactionList.add(transaction);
            savingAccountByUserId.setSavingAccountTransactionList(transactionList);
        }

    }

    // can only be added from the giroaccount ,ergo a new transaction has to be made on the giroaccount with  negative number, and a new transaction with a positive
    //number has to be made on the saving account

    public void saveNewTransactionForSavingAccountAndGiroAccount(long userId, Transaction transaction) {
        transaction.setDateAndTime(LocalDateTime.now());
        transactionService.saveTransaction(userId, transaction);
        addNewTransactionToSavingAccount(userId, transaction);
        //transaction of giro account
        Transaction transactionOfGiroAccount = new Transaction();
        transactionOfGiroAccount.setUserId(userId);
        transactionOfGiroAccount.setTransactionAmount(transaction.getTransactionAmount());
        transactionOfGiroAccount.setDescription(transaction.getDescription());
        transactionOfGiroAccount.setDateAndTime(transaction.getDateAndTime());
        if (transaction.getTransactionType().equals(TransactionType.INCOMING)) {
            transactionOfGiroAccount.setTransactionType(TransactionType.OUTGOING);
        } else {
            transactionOfGiroAccount.setTransactionType(TransactionType.INCOMING);
        }
        transactionService.saveTransaction(userId, transactionOfGiroAccount);
        giroAccountService.saveNewTransactionOnGiroAccountWithSavingAccount(userId, transactionOfGiroAccount);

    }


}
