package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.SavingAccount;
import com.bankApplication.BankAccountBE.repositories.SavingAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SavingAccountService {

    private final SavingAccountRepository savingAccountRepository;

    public SavingAccountService(SavingAccountRepository savingAccountRepository) {
        this.savingAccountRepository = savingAccountRepository;
    }

    public Optional<SavingAccount> findSavingAccountByUserId(long userId) {
        return savingAccountRepository.findSavingAccountByUserId(userId);
    }
}
