package com.bankApplication.BankAccountBE.models;

import java.util.List;

public class UserDTO {

    private PublicUser publicUser;
    private List<Transaction> transactionList;
    private SavingAccount savingAccount;
    private long totalSumOfTransactionAmount;

    public UserDTO(PublicUser publicUser, List<Transaction> transactionList, SavingAccount savingAccount, long totalSumOfTransactionAmount) {
        this.publicUser = publicUser;
        this.transactionList = transactionList;
        this.savingAccount = savingAccount;
        this.totalSumOfTransactionAmount = totalSumOfTransactionAmount;
    }


    public PublicUser getPublicUser() {
        return publicUser;
    }

    public void setPublicUser(PublicUser publicUser) {
        this.publicUser = publicUser;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

    public long getTotalSumOfTransactionAmount() {
        return totalSumOfTransactionAmount;
    }

    public void setTotalSumOfTransactionAmount(long totalSumOfTransactionAmount) {
        this.totalSumOfTransactionAmount = totalSumOfTransactionAmount;
    }
}
