package com.bankApplication.BankAccountBE.services;

import com.bankApplication.BankAccountBE.models.PublicUser;
import com.bankApplication.BankAccountBE.repositories.PublicUserRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicUserService {
    private final PublicUserRepository publicUserRepository;

    public PublicUserService(PublicUserRepository publicUserRepository) {
        this.publicUserRepository = publicUserRepository;
    }

    public PublicUser findPublicUserByUserId(long userId) {
        return publicUserRepository.findPublicUserByUserId(userId).orElse(null);
    }
}
