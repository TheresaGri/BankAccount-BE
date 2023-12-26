package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDTOService {

    private final PublicUserService publicUserService;
    private final SavingAccountService savingAccountService;
    private final TransactionService transactionService;
    private final GiroAccountService giroAccountService;

    public UserDTOService(PublicUserService publicUserService, SavingAccountService savingAccountService, TransactionService transactionService, GiroAccountService giroAccountService) {
        this.publicUserService = publicUserService;
        this.savingAccountService = savingAccountService;
        this.transactionService = transactionService;
        this.giroAccountService = giroAccountService;
    }


    public UserDTO getUserDTO(long userId) {
        PublicUser publicUserByUserId = publicUserService.findPublicUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("PublicUser not found with ID: " + userId));
        SavingAccount savingAccountByUserId = savingAccountService.findSavingAccountByUserId(userId)
                .orElseThrow(() -> new RuntimeException("SavingAccount not found with ID: " + userId));
        GiroAccount giroAccount = giroAccountService.getGiroAccountById(userId);
        UserDTO userDTO = new UserDTO(publicUserByUserId, giroAccount, savingAccountByUserId);
        userDTO.setGiroAccountTransactionSum(giroAccountService.getSumOfTransactionsOfGiroAccount(userId));
        userDTO.setSavingAccountTransactionSum(savingAccountService.getSumOfTransactionsOfSavingAccount(userId));

        return userDTO;
    }
}
