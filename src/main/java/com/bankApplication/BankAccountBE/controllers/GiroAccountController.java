package com.bankApplication.BankAccountBE.controllers;


import com.bankApplication.BankAccountBE.models.GiroAccount;
import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.services.GiroAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/giroAccount")
@CrossOrigin(origins = "http://localhost:5173")
public class GiroAccountController {
    private final GiroAccountService giroAccountService;

    public GiroAccountController(GiroAccountService giroAccountService) {
        this.giroAccountService = giroAccountService;
    }

    @GetMapping("/{userId}")
    public GiroAccount getGiroAccountByUserId(@PathVariable long userId) {
        return giroAccountService.getGiroAccountById(userId);
    }

    @GetMapping("/{userId}/sumOfTransactions")
    public long getSumOfTransactionsByUserId(@PathVariable long userId) {
        return giroAccountService.getSumOfTransactionsOfGiroAccount(userId);
    }
}
