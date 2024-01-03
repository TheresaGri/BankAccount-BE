package com.bankApplication.BankAccountBE.controllers;

import com.bankApplication.BankAccountBE.models.UserDTO;
import com.bankApplication.BankAccountBE.services.UserDTOService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDTO")
@CrossOrigin(origins = "http://localhost:5173")
public class UserDTOController {

    private final UserDTOService userDTOService;

    public UserDTOController(UserDTOService userDTOService) {
        this.userDTOService = userDTOService;
    }

    @GetMapping
    public List<UserDTO> getAllUserDTOs() {
        return userDTOService.getUserDTOList();
    }

    @GetMapping(value = "/{userId}", produces = "application/json")
    public UserDTO getUserDTOByUserId(@PathVariable long userId) {
        return userDTOService.getUserDTO(userId);
    }

}
