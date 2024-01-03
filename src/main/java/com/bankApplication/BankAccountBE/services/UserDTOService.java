package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDTOService {

    private final PublicUserService publicUserService;
    private final SavingAccountService savingAccountService;
    private final TransactionService transactionService;
    private final GiroAccountService giroAccountService;
    private final UserService userService;

    public UserDTOService(PublicUserService publicUserService, SavingAccountService savingAccountService, TransactionService transactionService, GiroAccountService giroAccountService, UserService userService) {
        this.publicUserService = publicUserService;
        this.savingAccountService = savingAccountService;
        this.transactionService = transactionService;
        this.giroAccountService = giroAccountService;
        this.userService = userService;
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

    public List<UserDTO> getUserDTOList() {
        List<User> allUsers = userService.findAllUsers();
        List<UserDTO> createdUserDTOs = new ArrayList<>();
        if(allUsers.size() != 0) {
            for(User u: allUsers) {
                createdUserDTOs.add(getUserDTO(u.getId()));
            }
        }
        return createdUserDTOs;
    }
}
