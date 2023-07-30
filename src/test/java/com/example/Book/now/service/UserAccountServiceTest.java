package com.example.Book.now.service;

import com.example.Book.now.RequestBodies.LoginRequestBody;
import com.example.Book.now.RequestBodies.RegisterRequestBody;
import com.example.Book.now.exceptions.EmailNotVerifiedException;
import com.example.Book.now.exceptions.UserAlreadyExistsException;
import com.example.Book.now.exceptions.UserDoesntExistsException;
import com.example.Book.now.responseBodies.AuthenticationDTO;
import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

import java.util.Date;

@SpringBootTest
public class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private JwtService jwtService;

    @RegisterExtension
    private static GreenMailExtension greenMailExtension = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("springboot", "secret"))
            .withPerMethodLifecycle(true);

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
    public void registerUserAccountTest() throws EmailNotVerifiedException, MessagingException {
        RegisterRequestBody requestBody = new RegisterRequestBody();
        requestBody.setEmail("admin@mail.com");
        requestBody.setPassword("testpass123");
        requestBody.setCity("test-city");
        requestBody.setPhone("test-phone");
        requestBody.setState("test-state");
        requestBody.setCountry("test-country");
        requestBody.setFirstName("test-firstname");
        requestBody.setLastName("test-lastname");
        requestBody.setDateOfBirth(new Date("2022/08/13"));
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userAccountService.registerUserAccount(requestBody), "Email shouldn't be already used");
        requestBody.setEmail("admin2@mail.com");
        Assertions.assertDoesNotThrow(() -> userAccountService.registerUserAccount(requestBody), "User should be able to register");
        Assertions.assertEquals(requestBody.getEmail(), greenMailExtension.getReceivedMessages()[0].getRecipients(Message.RecipientType.TO)[0].toString(), "Should receive verification email");

    }

}
