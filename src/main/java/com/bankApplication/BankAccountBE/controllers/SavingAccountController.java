package com.bankApplication.BankAccountBE.controllers;

import com.bankApplication.BankAccountBE.models.PublicUser;
import com.bankApplication.BankAccountBE.models.SavingAccount;
import com.bankApplication.BankAccountBE.models.Transaction;
import com.bankApplication.BankAccountBE.services.SavingAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/savingAccount")
@CrossOrigin(origins = "http://localhost:5173")
public class SavingAccountController {

    private final SavingAccountService savingAccountService;

    public SavingAccountController(SavingAccountService savingAccountService) {
        this.savingAccountService = savingAccountService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SavingAccount> getSavingAccountByUserId(@PathVariable Long userId) {
        Optional<SavingAccount> savingAccountOptional = savingAccountService.findSavingAccountByUserId(userId);
        return savingAccountOptional
                .map(savingAccount -> ResponseEntity.ok().body(savingAccount))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    public void saveNewTransactionOnSavingAccountAndGiroAccount(@PathVariable long userId,  @RequestBody Transaction newTransaction) {
        savingAccountService.saveNewTransactionForSavingAccountAndGiroAccount(userId, newTransaction);
    }
}
