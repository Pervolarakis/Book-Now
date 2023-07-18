package com.example.Book.now.controller;

import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.RequestBodies.LoginRequestBody;
import com.example.Book.now.responseBodies.AuthenticationDTO;
import com.example.Book.now.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/login")
    public AuthenticationDTO userAccountLogin(@RequestBody LoginRequestBody request){
        return userAccountService.loginUserAccount(request);
    }

    @PostMapping()
    public UserAccount getUser(@RequestBody LoginRequestBody request){
        return userAccountService.findUserAccountByEmail(request.getEmail());
    }

}
