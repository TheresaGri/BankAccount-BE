package com.bankApplication.BankAccountBE.repositories;

import com.bankApplication.BankAccountBE.models.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {
    Optional<SavingAccount> findSavingAccountByUserId(long userId);
}
