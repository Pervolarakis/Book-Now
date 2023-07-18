package com.example.Book.now.controller;

import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.RequestBodies.LoginRequestBody;
import com.example.Book.now.responseBodies.AuthenticationDTO;
import com.example.Book.now.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> userAccountLogin(@RequestBody LoginRequestBody request){
        return ResponseEntity.ok(userAccountService.loginUserAccount(request));
    }

    //TODO CHANGE TO DTO
    @PostMapping()
    public UserAccount getUser(@RequestBody LoginRequestBody request){
        return userAccountService.findUserAccountByEmail(request.getEmail());
    }

}
