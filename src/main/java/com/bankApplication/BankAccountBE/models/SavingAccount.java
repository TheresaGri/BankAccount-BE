package com.bankApplication.BankAccountBE.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SavingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long savingAccountAmount;

    private long userId;


    public SavingAccount(long savingAccountAmount, long userId) {
        this.savingAccountAmount = savingAccountAmount;
        this.userId = userId;
    }

    public SavingAccount() {
    }

    public long getSavingAccountAmount() {
        return savingAccountAmount;
    }

    public void setSavingAccountAmount(long savingAccountAmount) {
        this.savingAccountAmount = savingAccountAmount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
