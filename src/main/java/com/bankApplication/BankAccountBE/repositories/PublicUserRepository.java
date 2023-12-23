package com.bankApplication.BankAccountBE.repositories;

import com.bankApplication.BankAccountBE.models.PublicUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicUserRepository extends JpaRepository<PublicUser, Long> {
    Optional<PublicUser> findPublicUserByUserId(long userId);
}
