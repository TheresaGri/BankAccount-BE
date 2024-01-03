package com.bankApplication.BankAccountBE.exceptions;

public class NoTransactionByThisUserIdFound extends Exception{
    public NoTransactionByThisUserIdFound(String message) {
        super(message);
    }
}
