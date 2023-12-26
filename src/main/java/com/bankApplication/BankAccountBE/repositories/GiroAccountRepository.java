package com.bankApplication.BankAccountBE.repositories;

import com.bankApplication.BankAccountBE.models.GiroAccount;
import com.bankApplication.BankAccountBE.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiroAccountRepository extends JpaRepository<GiroAccount, Long> {
}
