package com.example.Book.now.service;

import com.example.Book.now.RequestBodies.LoginRequestBody;
import com.example.Book.now.exceptions.EmailNotVerifiedException;
import com.example.Book.now.exceptions.UserDoesntExistsException;
import com.example.Book.now.responseBodies.AuthenticationDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

@SpringBootTest
public class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private JwtService jwtService;

    @Test
    @Transactional
    public void loginUserAccountTest() throws EmailNotVerifiedException {
        LoginRequestBody requestBody = new LoginRequestBody();
        requestBody.setEmail("kpink0@telegraph11.co.uk");
        requestBody.setPassword("password1");
        Assertions.assertThrows(InternalAuthenticationServiceException.class, () -> userAccountService.loginUserAccount(requestBody), "Email should exist");
        requestBody.setEmail("kpink0@telegraph.co.uk");
        Assertions.assertThrows(BadCredentialsException.class, () -> userAccountService.loginUserAccount(requestBody), "Password should be correct");
        requestBody.setPassword("password");
        Assertions.assertThrows(EmailNotVerifiedException.class, () -> userAccountService.loginUserAccount(requestBody), "Email should be verified");
    }

    @Test
    @Transactional
    public void registerUserAccountTest() throws EmailNotVerifiedException {
        
    }

}
