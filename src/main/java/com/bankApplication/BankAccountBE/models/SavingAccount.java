package com.bankApplication.BankAccountBE.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class SavingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Transaction> savingAccountTransactionList;

    private long userId;


    public SavingAccount() {
    }

    public SavingAccount(List<Transaction> savingAccountTransactionList, long userId) {
        this.savingAccountTransactionList = savingAccountTransactionList;
        this.userId = userId;
    }



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Transaction> getSavingAccountTransactionList() {
        return savingAccountTransactionList;
    }

    public void setSavingAccountTransactionList(List<Transaction> savingAccountTransactionList) {
        this.savingAccountTransactionList = savingAccountTransactionList;
    }
}
