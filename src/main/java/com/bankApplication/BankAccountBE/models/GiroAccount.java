package com.bankApplication.BankAccountBE.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class GiroAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Transaction> giroAccountTransactions;
    private long userId;

    public GiroAccount(List<Transaction> giroAccountTransactions, long userId) {
        this.giroAccountTransactions = giroAccountTransactions;
        this.userId = userId;
    }

    public GiroAccount() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Transaction> getGiroAccountTransactions() {
        return giroAccountTransactions;
    }

    public void setGiroAccountTransactions(List<Transaction> giroAccountTransactions) {
        this.giroAccountTransactions = giroAccountTransactions;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
