package com.bankApplication.BankAccountBE.models;

import java.util.List;

public class UserDTO {

    private PublicUser publicUser;
    private GiroAccount giroAccount;
    private SavingAccount savingAccount;
    private long giroAccountTransactionSum;
    private long savingAccountTransactionSum;


    public UserDTO(PublicUser publicUser, GiroAccount giroAccount, SavingAccount savingAccount) {
        this.publicUser = publicUser;
        this.giroAccount = giroAccount;
        this.savingAccount = savingAccount;
    }


    public PublicUser getPublicUser() {
        return publicUser;
    }

    public void setPublicUser(PublicUser publicUser) {
        this.publicUser = publicUser;
    }



    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }


    public GiroAccount getGiroAccount() {
        return giroAccount;
    }

    public void setGiroAccount(GiroAccount giroAccount) {
        this.giroAccount = giroAccount;
    }

    public long getGiroAccountTransactionSum() {
        return giroAccountTransactionSum;
    }

    public void setGiroAccountTransactionSum(long giroAccountTransactionSum) {
        this.giroAccountTransactionSum = giroAccountTransactionSum;
    }

    public long getSavingAccountTransactionSum() {
        return savingAccountTransactionSum;
    }

    public void setSavingAccountTransactionSum(long savingAccountTransactionSum) {
        this.savingAccountTransactionSum = savingAccountTransactionSum;
    }
}
