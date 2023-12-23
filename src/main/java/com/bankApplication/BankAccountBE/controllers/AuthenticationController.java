package com.bankApplication.BankAccountBE.controllers;

import com.bankApplication.BankAccountBE.models.LoginResponseDTO;
import com.bankApplication.BankAccountBE.models.RegistrationDTO;
import com.bankApplication.BankAccountBE.models.User;
import com.bankApplication.BankAccountBE.services.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //have to add User and check if admin
    //if User that is entered has admin role, a new user can be registered

    /*
    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
    */
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(),body.getPassword());
    }
}
