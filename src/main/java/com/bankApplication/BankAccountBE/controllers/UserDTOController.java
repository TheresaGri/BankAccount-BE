package com.bankApplication.BankAccountBE.controllers;

import com.bankApplication.BankAccountBE.models.UserDTO;
import com.bankApplication.BankAccountBE.services.UserDTOService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userDTO")
@CrossOrigin(origins = "http://localhost:5173")
public class UserDTOController {

    private final UserDTOService userDTOService;

    public UserDTOController(UserDTOService userDTOService) {
        this.userDTOService = userDTOService;
    }

    @GetMapping(value = "/{userId}", produces = "application/json")
    public UserDTO getUserDTOByUserId(@PathVariable long userId) {
        return userDTOService.getUserDTO(userId);
    }

}
