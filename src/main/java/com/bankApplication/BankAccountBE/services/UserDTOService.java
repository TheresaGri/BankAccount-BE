package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.PublicUser;
import com.bankApplication.BankAccountBE.models.SavingAccount;
import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.models.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDTOService {

    private final PublicUserService publicUserService;
    private final SavingAccountService savingAccountService;
    private final TransactionService transactionService;

    public UserDTOService(PublicUserService publicUserService, SavingAccountService savingAccountService, TransactionService transactionService) {
        this.publicUserService = publicUserService;
        this.savingAccountService = savingAccountService;
        this.transactionService = transactionService;
    }

    public UserDTO getUserDTO(long userId) {
        PublicUser publicUserByUserId = publicUserService.findPublicUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("PublicUser not found with ID: " + userId));
        SavingAccount savingAccountByUserId = savingAccountService.findSavingAccountByUserId(userId)
                .orElseThrow(() -> new RuntimeException("SavingAccount not found with ID: " + userId));
        List<Transaction> transactionListByUserId = transactionService.findAllTransactionsByUserId(userId);
        return new UserDTO(publicUserByUserId, transactionListByUserId,savingAccountByUserId);
    }
}
