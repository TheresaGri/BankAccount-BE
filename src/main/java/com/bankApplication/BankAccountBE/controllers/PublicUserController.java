package com.bankApplication.BankAccountBE.controllers;

import com.bankApplication.BankAccountBE.models.PublicUser;
import com.bankApplication.BankAccountBE.services.PublicUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/publicUser")
@CrossOrigin(origins = "http://localhost:5173")

public class PublicUserController {
    private final PublicUserService publicUserService;

    public PublicUserController(PublicUserService publicUserService) {
        this.publicUserService = publicUserService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PublicUser> getPublicUserByUserId(@PathVariable Long userId) {
        Optional<PublicUser> publicUserOptional = publicUserService.findPublicUserByUserId(userId);

        return publicUserOptional
                .map(publicUser -> ResponseEntity.ok().body(publicUser))
                .orElse(ResponseEntity.notFound().build());
    }
}
