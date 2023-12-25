package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.PublicUser;
import com.bankApplication.BankAccountBE.repositories.PublicUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicUserService {
    private final PublicUserRepository publicUserRepository;

    public PublicUserService(PublicUserRepository publicUserRepository) {
        this.publicUserRepository = publicUserRepository;
    }

    public Optional<PublicUser> findPublicUserByUserId(long userId) {
        System.out.println(publicUserRepository.findPublicUserByUserId(userId));
        return publicUserRepository.findPublicUserByUserId(userId);
    }
}
