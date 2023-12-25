package com.bankApplication.BankAccountBE.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;

    private LocalDateTime dateAndTime;

    private long transactionAmount;

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


    public Transaction(long userId, LocalDateTime dateAndTime, long transactionAmount, String description, TransactionType transactionType) {
        this.userId = userId;
        this.dateAndTime = dateAndTime;
        this.transactionAmount = transactionAmount;
        this.description = description;
        this.transactionType = transactionType;
    }

    public Transaction() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
